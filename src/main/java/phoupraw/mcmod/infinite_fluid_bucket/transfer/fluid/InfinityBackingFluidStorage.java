package phoupraw.mcmod.infinite_fluid_bucket.transfer.fluid;

import com.google.common.collect.Iterators;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.fluid.Fluids;
import org.jetbrains.annotations.NotNull;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBFluidTags;
import phoupraw.mcmod.infinite_fluid_bucket.transfer.base.FilteringStorageView;

import java.util.Iterator;

@Deprecated
public record InfinityBackingFluidStorage(Storage<FluidVariant> back) implements Storage<FluidVariant> {
    @Override
    public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
        if (Fluids.EMPTY.isIn(IFBFluidTags.INFINITABLE)) {
            return StorageUtil.simulateInsert(back(), resource, maxAmount, transaction);
        }
        return 0;
    }
    @Override
    public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
        if (resource.getFluid().isIn(IFBFluidTags.INFINITABLE)) {
            return StorageUtil.simulateExtract(back(), resource, maxAmount, transaction);
        }
        return 0;
    }
    @Override
    public @NotNull Iterator<StorageView<FluidVariant>> iterator() {
        return Iterators.transform(back().iterator(), View::new);
    }
    private record View(StorageView<FluidVariant> delegate) implements FilteringStorageView<FluidVariant> {
        @Override
        public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            if (resource.getFluid().isIn(IFBFluidTags.INFINITABLE)) {
                return StorageUtil.simulateExtract(delegate(), resource, maxAmount, transaction);
            }
            return 0;
        }
    }
}
