package phoupraw.mcmod.infinite_fluid_bucket;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMaps;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.constant.*;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity.FixedInfiniteFluidStorage;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity.ForwardingInfiniteEmptyStorage;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity.ForwardingInfiniteFluidStorage;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity.TagFixedInfiniteEmptyStorage;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

/*
 -Dmixin.debug.export=false
 -Dmixin.debug.export.decompile=true
 -javaagent:"C:\Users\46793\.gradle\caches\modules-2\files-2.1\net.fabricmc\sponge-mixin\0.12.5+mixin.0.8.5\8d31fb97c3e0cd7c8dad3441851c523bcfae6d8e\sponge-mixin-0.12.5+mixin.0.8.5.jar"
 */
public final class InfiniteFluidBucket implements ModInitializer {
    public static final String ID = "infinite_fluid_bucket";
    public static final String NAME_KEY = "modmenu.nameTranslation." + ID;
    @ApiStatus.Internal
    public static final Logger LOGGER = LogManager.getLogger(ID);
    private static final Set<Item> REGISTERED = new HashSet<>();
    public static MutableText name() {
        return Text.translatable(NAME_KEY);
    }
    @SuppressWarnings("deprecation")
    public static boolean isIn(FluidVariant resource, TagKey<Fluid> tag) {
        return resource.getFluid().isIn(tag);
    }
    @Override
    public void onInitialize() {
        IFBConstants.loadClasses();
        //for (var entry : IFBConfig.ITEM_OPTIONS.entrySet()) {
        //    Item item = entry.getKey();
        //    BooleanSupplier option = entry.getValue();
        //    FluidStorage.ITEM.getSpecificFor(item).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        //    FluidStorage.ITEM.getSpecificFor(item).register(IFBIDs.PHASE, (itemStack, context) -> option.getAsBoolean() && Infinities.hasInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        //}
        FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).register((itemStack, context) -> Infinities.isInfinity(itemStack) ? new FixedInfiniteFluidStorage(context, FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET) : null);
        FluidStorage.ITEM.getSpecificFor(Items.LAVA_BUCKET).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.LAVA_BUCKET).register((itemStack, context) -> Infinities.isInfinity(itemStack) ? new FixedInfiniteFluidStorage(context, FluidVariant.of(Fluids.LAVA), FluidConstants.BUCKET) : null);
        FluidStorage.ITEM.getSpecificFor(Items.BUCKET).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.BUCKET).register((itemStack, context) -> Infinities.isInfinity(itemStack) ? new TagFixedInfiniteEmptyStorage(context, FluidConstants.BUCKET, IFBFluidTags.BUCKET) : null);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).register((itemStack, context) -> Infinities.isInfinity(itemStack) ? new FixedInfiniteFluidStorage(context, FluidVariant.of(Fluids.WATER), FluidConstants.BOTTLE) : null);
        FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).register((itemStack, context) -> Infinities.isInfinity(itemStack) ? new TagFixedInfiniteEmptyStorage(context, FluidConstants.BOTTLE, IFBFluidTags.GLASS_BOTTLE) : null);
        FluidStorage.ITEM.getSpecificFor(Items.MILK_BUCKET).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.MILK_BUCKET).register(InfiniteFluidBucket::findGeneralInfStorage);
        FluidStorage.ITEM.getSpecificFor(Items.HONEY_BOTTLE).addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.HONEY_BOTTLE).register(InfiniteFluidBucket::findGeneralInfStorage);
        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) -> enchantment.isIn(IFBEnchantmentTags.INFINITIER) && Infinities.canInfinity(target) ? TriState.TRUE : TriState.DEFAULT);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> reflectTagChanges());
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            reflectTagChanges();
            for (Map.Entry<String, GameRules.Key<GameRules.BooleanRule>> entry : IFBGameRules.KEYS.entrySet()) {
                String name = entry.getKey();
                GameRules.Key<GameRules.BooleanRule> key = entry.getValue();
                IFBGameRules.VALUES.put(name, server.getGameRules().getBoolean(key));
            }
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            for (Object2BooleanMap.Entry<String> entry : Object2BooleanMaps.fastIterable(IFBGameRules.VALUES)) {
                sender.sendPacket(new IFBGameRules.BoolGameRulePayload(entry.getKey(), entry.getBooleanValue()));
            }
        });
        PayloadTypeRegistry.playS2C().register(IFBGameRules.BoolGameRulePayload.ID, IFBGameRules.BoolGameRulePayload.CODEC);
        //CauldronBehavior behavior = CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().get(Items.POTION);
        //CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.map().put(Items.POTION, new CauldronBehavior() {
        //    @Override
        //    public ItemActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        //        if (Infinities.isInfinity(stack)) {
        //
        //        }
        //    }
        //});
        //AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
        //    ItemStack itemStack = player.getStackInHand(hand);
        //    if (!Infinities.isInfinity(itemStack)) return ActionResult.PASS;
        //    Storage<FluidVariant> itemStorage = ContainerItemContext.forPlayerInteraction(player, hand).find(FluidStorage.ITEM);
        //    if (itemStorage == null) return ActionResult.PASS;
        //    Storage<FluidVariant> blockStorage = FluidStorage.SIDED.find(world, pos, direction);
        //    if (blockStorage == null) return ActionResult.PASS;
        //    if (world.isClient()) return ActionResult.SUCCESS;
        //    var moved = Storages.move(blockStorage, itemStorage, null);
        //    if (moved.getRight() <= 0) return ActionResult.FAIL;
        //    Set<SoundEvent> sounds = new HashSet<>();
        //    for (FluidVariant variant : moved.getLeft().keySet()) {
        //        Optional<SoundEvent> sound0 = variant.getFluid().getBucketFillSound();
        //        if (sound0.isPresent()) {
        //            sounds.add(sound0.get());
        //        }
        //    }
        //    for (SoundEvent sound : sounds) {
        //        world.playSound(null, pos, sound, SoundCategory.PLAYERS, 1, 1);
        //    }
        //    return ActionResult.SUCCESS;
        //});
        //UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
        //    ItemStack itemStack = player.getStackInHand(hand);
        //    if (!Infinities.isInfinity(itemStack)) return ActionResult.PASS;
        //    Storage<FluidVariant> itemStorage = ContainerItemContext.forPlayerInteraction(player, hand).find(FluidStorage.ITEM);
        //    if (itemStorage == null) return ActionResult.PASS;
        //    BlockPos pos = hitResult.getBlockPos();
        //    Storage<FluidVariant> blockStorage = FluidStorage.SIDED.find(world, pos, hitResult.getSide());
        //    if (blockStorage == null) return ActionResult.PASS;
        //    if (world.isClient()) return ActionResult.SUCCESS;
        //    Item item = itemStack.getItem();
        //    var moved = Storages.move(itemStorage,blockStorage,  null);
        //    if (moved.getRight() <= 0) return ActionResult.FAIL;
        //    if (item instanceof ABucketItem bucketItem) {
        //        bucketItem.invokePlayEmptyingSound(player,world,pos);
        //    }else{
        //        world.playSound(null, pos,item==Items.POTION?SoundEvents.ITEM_BOTTLE_EMPTY: SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1, 1);
        //    }
        //    return ActionResult.SUCCESS;
        //});
        //ServerLifecycleEvents.SERVER_STARTING.register(Infinities::setServer);
        //ServerLifecycleEvents.SERVER_STOPPED.register(Infinities::unsetServer);
    }
    private static Storage<FluidVariant> findGeneralInfStorage(ItemStack itemStack, ContainerItemContext context) {return Infinities.isInfinity(itemStack) ? ForwardingInfiniteFluidStorage.of(findNonInfinityStorage(itemStack, context)) : null;}
    static void reflectTagChanges() {
        register(IFBItemTags.INSERTABLE, ForwardingInfiniteEmptyStorage::of);
        register(IFBItemTags.EXTRACTABLE, ForwardingInfiniteFluidStorage::of);
    }
    public static @Nullable Storage<FluidVariant> findNonInfinityStorage(ItemStack stack, ContainerItemContext context) {
        return FluidStorage.ITEM.find(Infinities.removeInfinity(stack), context);
    }
    private static void register(TagKey<Item> itemTag, UnaryOperator<@Nullable Storage<FluidVariant>> factory) {
        for (RegistryEntry<Item> entry : Registries.ITEM.iterateEntries(itemTag)) {
            Item item = entry.value();
            if (!REGISTERED.add(item)) continue;
            var specific = FluidStorage.ITEM.getSpecificFor(item);
            specific.addPhaseOrdering(IFBIDs.PHASE, Event.DEFAULT_PHASE);
            specific.register(IFBIDs.PHASE, (itemStack, context) -> {
                if (itemStack.isIn(itemTag) && Infinities.hasInfinity(itemStack)) {
                    ItemStack noInf = Infinities.removeInfinity(itemStack.copy());
                    Storage<FluidVariant> nonInfS = FluidStorage.ITEM.find(noInf, context);
                    return factory.apply(nonInfS);
                }
                return null;
            });
        }
    }
}
