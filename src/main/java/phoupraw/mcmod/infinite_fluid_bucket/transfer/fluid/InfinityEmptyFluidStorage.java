package phoupraw.mcmod.infinite_fluid_bucket.transfer.fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.base.InfinityEmptyStorage;

public interface InfinityEmptyFluidStorage extends InfinityEmptyStorage<FluidVariant> {
    @Override
    default FluidVariant getResource() {
        return FluidVariant.blank();
    }
}
