package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

public interface FilteringStorageView<T> extends StorageView<T> {
    @Override
    default long extract(T resource, long maxAmount, TransactionContext transaction) {
        return canExtract(resource) ? delegate().extract(resource, maxAmount, transaction) : 0;
    }
    @Override
    default boolean isResourceBlank() {
        return delegate().isResourceBlank();
    }
    @Override
    default T getResource() {
        return delegate().getResource();
    }
    @Override
    default long getAmount() {
        return delegate().getAmount();
    }
    @Override
    default long getCapacity() {
        return delegate().getCapacity();
    }
    @Override
    default StorageView<T> getUnderlyingView() {
        return delegate().getUnderlyingView();
    }
    default boolean canExtract(T resource) {
        return true;
    }
    StorageView<T> delegate();
}
