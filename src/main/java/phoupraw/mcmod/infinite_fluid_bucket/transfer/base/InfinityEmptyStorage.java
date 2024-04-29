package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.InsertionOnlyStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public interface InfinityEmptyStorage<T> extends SingleSlotStorage<T>, InsertionOnlyStorage<T> {
    static <T> InfinityEmptyStorage<T> of(T blankResource, long capacity) {
        return new InfinityEmptyStorageImpl<>(blankResource, capacity);
    }
    @Override
    default boolean isResourceBlank() {
        return true;
    }
    @Override
    default long getAmount() {
        return 0;
    }
    @Override
    default long insert(T resource, long maxAmount, TransactionContext transaction) {
        return canInsert(resource) && maxAmount >= getCapacity() ? getCapacity() : 0;
    }
    @Override
    default @NotNull Iterator<StorageView<T>> iterator() {
        return SingleSlotStorage.super.iterator();
    }
    default boolean canInsert(T resource) {
        return true;
    }
    @Override
    default long extract(T resource, long maxAmount, TransactionContext transaction) {
        return InsertionOnlyStorage.super.extract(resource, maxAmount, transaction);
    }
}
