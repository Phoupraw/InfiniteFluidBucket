package phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.InsertionOnlyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBFluidTags;

public final class ForwardingInfiniteEmptyStorage implements InsertionOnlyStorage<FluidVariant> {
    @Contract(value = "null -> null; !null -> new", pure = true)
    public static @Nullable ForwardingInfiniteEmptyStorage of(@Nullable Storage<FluidVariant> delegate) {
        return delegate == null ? null : new ForwardingInfiniteEmptyStorage(delegate);
    }
    public final Storage<FluidVariant> delegate;
    public ForwardingInfiniteEmptyStorage(Storage<FluidVariant> delegate) {this.delegate = delegate;}
    @Override
    public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
        return InfiniteFluidBucket.isIn(resource, IFBFluidTags.INSERTABLE) ? StorageUtil.simulateInsert(delegate, resource, maxAmount, transaction) : 0;
    }
    @Override
    public String toString() {
        return "ForwardingInfiniteEmptyStorage(" + delegate + ')';
    }
}
