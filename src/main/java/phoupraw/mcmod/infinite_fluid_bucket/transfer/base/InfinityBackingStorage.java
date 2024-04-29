package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import com.google.common.collect.Iterators;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.FilteringStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

import java.util.Iterator;

public class InfinityBackingStorage<T> extends FilteringStorage<T> {
    public static <T> @NotNull Storage<T> find(ItemStack any, ItemApiLookup<Storage<T>, ContainerItemContext> lookup) {
        return of(ContainerItemContext.withConstant(Infinities.removeInfinity(any.copy())).find(lookup));
    }
    public static <T> @NotNull Storage<T> of(@Nullable Storage<T> back) {
        return back == null ? Storage.empty() : new InfinityBackingStorage<>(back);
    }
    public InfinityBackingStorage(Storage<T> backingStorage) {
        super(backingStorage);
    }
    
    @Override
    public long insert(T resource, long maxAmount, TransactionContext transaction) {
        try (var t = Transaction.openNested(transaction)) {
            return super.insert(resource, maxAmount, t);
        }
    }
    @Override
    public long extract(T resource, long maxAmount, TransactionContext transaction) {
        try (var t = Transaction.openNested(transaction)) {
            return super.extract(resource, maxAmount, t);
        }
    }
    @Override
    public @NotNull Iterator<StorageView<T>> iterator() {
        return Iterators.transform(backingStorage.get().iterator(), FilteringStorageView::new);
    }
    @Override
    public Iterator<StorageView<T>> nonEmptyIterator() {
        return Iterators.transform(backingStorage.get().nonEmptyIterator(), FilteringStorageView::new);
    }
    protected class FilteringStorageView implements phoupraw.mcmod.infinite_fluid_bucket.transfer.base.FilteringStorageView<T> {
        private final StorageView<T> backingView;
        
        private FilteringStorageView(StorageView<T> backingView) {
            this.backingView = backingView;
        }
        @Override
        public long extract(T resource, long maxAmount, TransactionContext transaction) {
            try (var t = Transaction.openNested(transaction)) {
                return phoupraw.mcmod.infinite_fluid_bucket.transfer.base.FilteringStorageView.super.extract(resource, maxAmount, t);
            }
        }
        @Override
        public boolean canExtract(T resource) {
            return phoupraw.mcmod.infinite_fluid_bucket.transfer.base.FilteringStorageView.super.canExtract(resource) && InfinityBackingStorage.this.canExtract(resource);
        }
        @Override
        public StorageView<T> delegate() {
            return backingView;
        }
    }
}
