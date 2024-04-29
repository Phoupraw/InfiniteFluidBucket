package phoupraw.mcmod.infinite_fluid_bucket.transfer.fluid;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.minecraft.item.Items;

public class InfinityEmptyBucketStorage implements InfinityEmptyFluidStorage {
    public static final InfinityEmptyBucketStorage INSTANCE = new InfinityEmptyBucketStorage();
    @Override
    public long getCapacity() {
        return FluidConstants.BUCKET;
    }
    @Override
    public boolean canInsert(FluidVariant resource) {
        Storage<FluidVariant> storage = ContainerItemContext.withConstant(Items.BUCKET.getDefaultStack()).find(FluidStorage.ITEM);
        return InfinityEmptyFluidStorage.super.canInsert(resource) && storage != null && StorageUtil.simulateInsert(storage, resource, getCapacity(), null) == getCapacity();
    }
}
