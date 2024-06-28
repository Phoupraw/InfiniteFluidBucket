package phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity;

import com.google.common.collect.Iterators;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ExtractionOnlyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBFluidTags;

import java.util.Iterator;

public final class ForwardingInfiniteFluidStorage implements ExtractionOnlyStorage<FluidVariant> {
    @Contract(value = "null -> null; !null -> new", pure = true)
    public static @Nullable ForwardingInfiniteFluidStorage of(@Nullable Storage<FluidVariant> delegate) {
        return delegate == null ? null : new ForwardingInfiniteFluidStorage(delegate);
    }
    public final Storage<FluidVariant> delegate;
    public ForwardingInfiniteFluidStorage(Storage<FluidVariant> delegate) {this.delegate = delegate;}
    
    @Override
    public String toString() {
        return "ForwardingInfiniteFluidStorage(" + delegate + ')';
    }
    @Override
    public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
        return InfiniteFluidBucket.isIn(resource, IFBFluidTags.EXTRACTABLE) ? StorageUtil.simulateExtract(delegate, resource, maxAmount, transaction) : 0;
    }
    @Override
    public @NotNull Iterator<StorageView<FluidVariant>> iterator() {
        return Iterators.transform(delegate.iterator(), View::new);
    }
    
    private static final class View implements StorageView<FluidVariant> {
        public final StorageView<FluidVariant> delegate;
        public View(StorageView<FluidVariant> delegate) {this.delegate = delegate;}
        public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            return InfiniteFluidBucket.isIn(resource, IFBFluidTags.EXTRACTABLE) ? StorageUtil.simulateExtract(delegate, resource, maxAmount, transaction) : 0;
        }
        public boolean isResourceBlank() {return this.delegate.isResourceBlank();}
        public FluidVariant getResource() {return this.delegate.getResource();}
        public long getAmount() {return this.delegate.getAmount();}
        public long getCapacity() {return this.delegate.getCapacity();}
        public StorageView<FluidVariant> getUnderlyingView() {return this.delegate.getUnderlyingView();}
        @Override
        public String toString() {
            return "ForwardingInfiniteFluidStorage.View(" + delegate + ')';
        }
    }
}
