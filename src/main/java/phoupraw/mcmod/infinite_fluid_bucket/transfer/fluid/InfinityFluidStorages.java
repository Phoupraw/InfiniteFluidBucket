package phoupraw.mcmod.infinite_fluid_bucket.transfer.fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.minecraft.fluid.Fluids;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.base.InfinityFullStorage;

public interface InfinityFluidStorages {
    SingleSlotStorage<FluidVariant> BUCKET_WATER = InfinityFullStorage.of(FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET);
    SingleSlotStorage<FluidVariant> BUCKET_EMPTY = InfinityEmptyBucketStorage.INSTANCE;
    SingleSlotStorage<FluidVariant> BOTTLE_WATER = InfinityFullStorage.of(FluidVariant.of(Fluids.WATER), FluidConstants.BOTTLE);
    SingleSlotStorage<FluidVariant> BOTTLE_EMPTY = InfinityEmptyBottleStorage.INSTANCE;
}
