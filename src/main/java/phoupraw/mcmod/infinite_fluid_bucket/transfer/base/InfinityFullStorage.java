package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import net.fabricmc.fabric.api.transfer.v1.storage.base.ExtractionOnlyStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.NotNull;

public interface InfinityFullStorage<T> extends SingleSlotStorage<T>, ExtractionOnlyStorage<T> {
    static <T> InfinityFullStorage<T> of(T resource, long amount) {
        return new InfinityFullStorageImpl<>(resource, amount);
    }
    @Override
    default boolean isResourceBlank() {
        return false;
    }
    @Override
    default long getCapacity() {
        return getAmount();
    }
    @Override
    default long extract(T resource, long maxAmount, @NotNull TransactionContext transaction) {
        return resource.equals(getResource()) && maxAmount >= getAmount() ? getAmount() : 0;
    }
}
