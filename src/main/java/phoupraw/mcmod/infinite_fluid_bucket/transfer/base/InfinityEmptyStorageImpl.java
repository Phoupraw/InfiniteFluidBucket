package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class InfinityEmptyStorageImpl<T> implements InfinityEmptyStorage<T> {
    private final T blankResource;
    private final long capacity;
    public InfinityEmptyStorageImpl(T blankResource, long capacity) {
        this.blankResource = blankResource;
        this.capacity = capacity;
    }
    public T getResource() {return blankResource;}
    public long getCapacity() {return capacity;}
    @Override
    public String toString() {
        return "InfinityEmptyStorageImpl{blankResource=%s, capacity=%d}".formatted(getResource(), getCapacity());
    }
}
