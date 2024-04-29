package phoupraw.mcmod.infinite_fluid_bucket;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Misc;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.base.InfinityBackingStorage;

import java.util.function.BooleanSupplier;

public final class InfiniteFluidBucket implements ModInitializer {
    public static final String ID = "infinite_fluid_bucket";
    public static final String NAME = "modmenu.nameTranslation." + ID;
    @Override
    public void onInitialize() {
        Identifier phase = new Identifier(ID, "common");
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
            entries.addAfter(Items.WATER_BUCKET, Infinities.WATER_BUCKET);
            entries.addAfter(Items.BUCKET, Infinities.EMTPY_BUCKET);
            entries.addAfter(Items.MILK_BUCKET, Infinities.MILK_BUCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Misc.WATER_POTION, Infinities.WATER_BOTTLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.GLASS_BOTTLE, Infinities.EMPTY_BOTTLE);
        });
        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) -> enchantment == Enchantments.INFINITY && Infinities.canInfinity(target) ? TriState.TRUE : TriState.DEFAULT);
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
    }
}
