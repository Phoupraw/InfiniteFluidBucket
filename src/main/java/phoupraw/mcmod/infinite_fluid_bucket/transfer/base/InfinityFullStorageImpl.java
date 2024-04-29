package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class InfinityFullStorageImpl<T> implements InfinityFullStorage<T> {
    private final T resource;
    private final long amount;
    public InfinityFullStorageImpl(T resource, long amount) {
        this.resource = resource;
        this.amount = amount;
    }
    @Override
    public T getResource() {
        return resource;
    }
    @Override
    public long getAmount() {
        return amount;
    }
    @Override
    public String toString() {
        return "InfinityFullStorageImpl{resource=%s, amount=%d}".formatted(getResource(), getAmount());
    }
}
