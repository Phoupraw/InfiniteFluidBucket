package phoupraw.mcmod.infinite_fluid_bucket;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.fluid.InfinityFluidStorages;

import static phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig.HANDLER;

public final class InfiniteFluidBucket implements ModInitializer {
    public static final String ID = "infinite_fluid_bucket";
    public static final String NAME = "modmenu.nameTranslation." + ID;
    @Override
    public void onInitialize() {
        Identifier phase = new Identifier(ID, "common");
        FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.WATER_BUCKET).register((itemStack, context) -> HANDLER.instance().isWaterBucket() && Infinities.isInfinity(itemStack) ? InfinityFluidStorages.BUCKET_WATER : null);
        FluidStorage.ITEM.getSpecificFor(Items.BUCKET).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.BUCKET).register((itemStack, context) -> HANDLER.instance().isEmptyBucket() && Infinities.isInfinity(itemStack) ? InfinityFluidStorages.BUCKET_EMPTY : null);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.POTION).register((itemStack, context) -> HANDLER.instance().isWaterPotion() && Infinities.isPotionWater(itemStack) && Infinities.isInfinity(itemStack) ? InfinityFluidStorages.BOTTLE_WATER : null);
        FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).addPhaseOrdering(phase, Event.DEFAULT_PHASE);
        FluidStorage.ITEM.getSpecificFor(Items.GLASS_BOTTLE).register((itemStack, context) -> HANDLER.instance().isGlassBottle() && Infinities.isInfinity(itemStack) ? InfinityFluidStorages.BOTTLE_EMPTY : null);
        
        //DispenserBlock.registerBehavior();
    }
}
