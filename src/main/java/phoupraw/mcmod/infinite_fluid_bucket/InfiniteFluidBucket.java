package phoupraw.mcmod.infinite_fluid_bucket;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBIDs;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBRegistryInitializer;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.base.InfinityBackingStorage;

import java.util.function.BooleanSupplier;

/*
 -Dmixin.debug.export=false
 -Dmixin.debug.export.decompile=true
 -javaagent:"C:\Users\46793\.gradle\caches\modules-2\files-2.1\net.fabricmc\sponge-mixin\0.12.5+mixin.0.8.5\8d31fb97c3e0cd7c8dad3441851c523bcfae6d8e\sponge-mixin-0.12.5+mixin.0.8.5.jar"
 */
public final class InfiniteFluidBucket implements ModInitializer {
    public static final String ID = "infinite_fluid_bucket";
    public static final String NAME = "modmenu.nameTranslation." + ID;
    public static final Logger LOGGER = LogManager.getLogger(ID);
    @Override
    public void onInitialize() {
        Identifier phase = IFBIDs.of("common");
        for (var entry : IFBConfig.ITEM_OPTIONS.entrySet()) {
            Item item = entry.getKey();
            BooleanSupplier option = entry.getValue();
            FluidStorage.ITEM.getSpecificFor(item).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
            FluidStorage.ITEM.getSpecificFor(item).register(phase, (itemStack, context) -> option.getAsBoolean() && Infinities.hasInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        }
        //FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        //FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).register((itemStack, context) -> HANDLER.instance().isWaterBucket() && Infinities.isInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        //FluidStorage.ITEM.getSpecificFor(Items.BUCKET).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        //FluidStorage.ITEM.getSpecificFor(Items.BUCKET).register((itemStack, context) -> HANDLER.instance().isEmptyBucket() && Infinities.isInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).register((itemStack, context) -> Infinities.isPotionInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        //FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        //FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).register((itemStack, context) -> Infinities.isGlassBottleInfinity(itemStack) ? InfinityBackingStorage.find(itemStack, FluidStorage.ITEM) : null);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            RegistryEntry<Enchantment> infinity = Infinities.getInfinity(entries.getContext().lookup());
            if (IFBConfig.getConfig().isEmptyBucket()) {
                ItemStack stack = Items.WATER_BUCKET.getDefaultStack();
                stack.addEnchantment(infinity, 1);
                entries.addAfter(Items.WATER_BUCKET, stack);
            }
            if (IFBConfig.getConfig().isWaterBucket()) {
                ItemStack stack = Items.BUCKET.getDefaultStack();
                stack.addEnchantment(infinity, 1);
                entries.addAfter(Items.BUCKET, stack);
            }
            if (IFBConfig.getConfig().isMilkBucket()) {
                ItemStack stack = Items.MILK_BUCKET.getDefaultStack();
                stack.addEnchantment(infinity, 1);
                entries.addAfter(Items.MILK_BUCKET, stack);
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            if (IFBConfig.getConfig().isWaterPotion()) {
                RegistryEntry<Enchantment> infinity = Infinities.getInfinity(entries.getContext().lookup());
                ItemStack water = PotionContentsComponent.createStack(Items.POTION, Potions.WATER);
                ItemStack stack = water.copy();
                stack.addEnchantment(infinity, 1);
                entries.addAfter(water, stack);
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            if (IFBConfig.getConfig().isGlassBottle()) {
                RegistryEntry<Enchantment> infinity = Infinities.getInfinity(entries.getContext().lookup());
                ItemStack stack = Items.GLASS_BOTTLE.getDefaultStack();
                stack.addEnchantment(infinity, 1);
                entries.addAfter(Items.GLASS_BOTTLE, stack);
            }
        });
        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) -> enchantment.getKey().orElseThrow() == Enchantments.INFINITY && Infinities.canInfinity(target) ? TriState.TRUE : TriState.DEFAULT);
        new IFBRegistryInitializer();
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
}
