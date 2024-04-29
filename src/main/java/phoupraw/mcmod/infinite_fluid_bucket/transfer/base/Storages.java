package phoupraw.mcmod.infinite_fluid_bucket.transfer.base;

import com.google.common.collect.Streams;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Storages {
    public static final ResourceAmount<ItemVariant> BLANK_ITEM = new ResourceAmount<>(ItemVariant.blank(), 0);
    public static final ResourceAmount<FluidVariant> BLANK_FLUID = new ResourceAmount<>(FluidVariant.blank(), 0);
    //public static final Codec<ResourceAmount<ItemVariant>> ITEM_COMPONENT = resourceAmountCodec(ItemVariant.CODEC);
    //public static final Codec<ResourceAmount<FluidVariant>> FLUID_COMPONENT = resourceAmountCodec(FluidVariant.CODEC);
    public static boolean isEmpty(Storage<?> self) {
        return !self.nonEmptyIterator().hasNext();
    }
    public static boolean isEmpty(StorageView<?> self) {
        return self.isResourceBlank() || self.getAmount() == 0;
    }
    public static boolean isEmpty(SingleSlotStorage<?> self) {
        return isEmpty((StorageView<?>) self);
    }
    public static boolean isFull(Iterable<? extends StorageView<?>> self) {
        return Streams.stream(self).allMatch(Storages::isFull);
    }
    public static boolean isFull(StorageView<?> self) {
        return self.getCapacity() == 0 || !self.isResourceBlank() && self.getAmount() == self.getCapacity();
    }
    public static boolean isFull(SingleSlotStorage<?> self) {
        return isFull((StorageView<?>) self);
    }
    public static long getCapacity(Iterable<? extends StorageView<?>> self) {
        return Streams.stream(self).mapToLong(StorageView::getCapacity).sum();
    }
    @SuppressWarnings("deprecation")
    public static <T> long getCapacity(Storage<T> self, T resource) {
        try (var t = Transaction.openNested(Transaction.getCurrentUnsafe())) {
            //System.out.println(self+" " + StorageUtil.move(self, BlackHoleStorage.of(), Predicates.alwaysTrue(), Long.MAX_VALUE, t));
            //StorageUtil.move(self, BlackHoleStorage.of(), Predicates.alwaysTrue(), Long.MAX_VALUE, t);
            extractAll(self, t);
            return self.insert(resource, Long.MAX_VALUE, t);
        }
    }
    @SuppressWarnings("deprecation")
    public static <T> long getCapacity(SingleSlotStorage<T> self, T resource) {
        try (var t = Transaction.openNested(Transaction.getCurrentUnsafe())) {
            if (!self.isResourceBlank()) {
                self.extract(self.getResource(), self.getAmount(), t);
            }
            return self.insert(resource, Long.MAX_VALUE, t);
        }
    }
    public static long getAmount(Iterable<? extends StorageView<?>> self) {
        long amount = 0;
        for (StorageView<?> view : self) {
            amount += view.getAmount();
        }
        return amount;
    }
    public static <T> long getAmount(Iterable<? extends StorageView<@NotNull T>> self, T resource) {
        long amount = 0;
        for (StorageView<T> view : self) {
            if (resource.equals(view.getResource())) {
                amount += view.getAmount();
            }
        }
        return amount;
    }
    public static double getOccupancy(StorageView<?> self) {
        return (double) self.getAmount() / self.getCapacity();
    }
    public static double getOccupancy(Iterable<? extends StorageView<?>> self) {
        return (double) getAmount(self) / getCapacity(self);
    }
    public static double getOccupancy(SingleSlotStorage<?> self) {
        return getOccupancy((StorageView<?>) self);
    }
    //public static void readItem(DirectSingleVariantStorage<ItemVariant> self, NbtCompound nbtSlot) {
    //    ItemVariant variant = ItemVariant.fromNbt(nbtSlot.getCompound("variant"));
    //    long amount = variant.isBlank() ? 0 : NamedBinaryTags.getLong(nbtSlot, "amount", 1);
    //    self.setResource(variant);
    //    self.setAmount(amount);
    //}
    //public static void readItems(List<? extends DirectSingleVariantStorage<ItemVariant>> self, NbtList nbtSlots) {
    //    for (var slot : self) {
    //        slot.setResource(ItemVariant.blank());
    //    }
    //    for (int i = 0; i < nbtSlots.size(); i++) {
    //        NbtCompound nbtSlot = nbtSlots.getCompound(i);
    //        int slotIndex = nbtSlot.getInt("slot");
    //        var slot = self.get(slotIndex);
    //        readItem(slot, nbtSlot);
    //    }
    //}
    //public static void readFluid(DirectSingleVariantStorage<FluidVariant> self, NbtCompound nbtSlot) {
    //    FluidVariant variant = FluidVariant.fromNbt(nbtSlot.getCompound("variant"));
    //    long amount = variant.isBlank() ? 0 : NamedBinaryTags.getLong(nbtSlot, "amount", 1);
    //    self.setResource(variant);
    //    self.setAmount(amount);
    //}
    //public static void readFluids(List<? extends DirectSingleVariantStorage<FluidVariant>> self, NbtList nbtSlots) {
    //    for (var slot : self) {
    //        slot.setResource(FluidVariant.blank());
    //    }
    //    for (int i = 0; i < nbtSlots.size(); i++) {
    //        NbtCompound nbtSlot = nbtSlots.getCompound(i);
    //        int slotIndex = nbtSlot.getInt("slot");
    //        var slot = self.get(slotIndex);
    //        readFluid(slot, nbtSlot);
    //    }
    //}
    //public void readSequenced(List<? extends DirectSingleItemStorage> self, NbtList nbtSlots) {
    //    for (int i = 0; i < self.size(); i++) {
    //        self.get(i).readNbt(nbtSlots.getCompound(i));
    //    }
    //}
    //public static @NotNull NbtList toNbt(List<? extends StorageView<? extends TransferVariant<?>>> self) {
    //    NbtList nbtSlots = new NbtList();
    //    for (var iterator = self.listIterator(); iterator.hasNext(); ) {
    //        int index = iterator.nextIndex();
    //        var slot = iterator.next();
    //        if (isEmpty(slot))continue;
    //        NbtCompound nbtSlot = toNbt(slot);
    //        nbtSlot.putInt("slot", index);
    //        nbtSlots.add(nbtSlot);
    //    }
    //    return nbtSlots;
    //}
    //public static @NotNull NbtCompound toNbt(StorageView<? extends TransferVariant<?>> self) {
    //    //nbt.put("variant", self.getResource().toNbt());
    //    //if (self.getAmount() != 1) {
    //    //    nbt.putLong("amount", self.getAmount());
    //    //}
    //    return toNbt(self.getResource(), self.getAmount());
    //}
    //public static @NotNull ResourceAmount<ItemVariant> toItem(@Nullable NbtCompound nbtSlot) {
    //    if (nbtSlot == null || nbtSlot.isEmpty()) return new ResourceAmount<>(ItemVariant.blank(), 0);
    //    ItemVariant variant = ItemVariant.fromNbt(nbtSlot.getCompound("variant"));
    //    long amount = NamedBinaryTags.getLong(nbtSlot, "amount", 1);
    //    return new ResourceAmount<>(variant, amount);
    //}
    //public static @NotNull ResourceAmount<FluidVariant> toFluid(@Nullable NbtCompound nbtSlot) {
    //    if (nbtSlot == null || nbtSlot.isEmpty()) return new ResourceAmount<>(FluidVariant.blank(), 0);
    //    FluidVariant variant = FluidVariant.fromNbt(nbtSlot.getCompound("variant"));
    //    long amount = NamedBinaryTags.getLong(nbtSlot, "amount", 1);
    //    return new ResourceAmount<>(variant, amount);
    //}
    /**
     @see #move(Storage, Storage, long, TransactionContext)
     */
    public static <T> long move(@Nullable Storage<? super T> from, @Nullable Storage<? super T> to, T resource, @Range(from = 0, to = Long.MAX_VALUE) long maxAmount, @Nullable TransactionContext transaction) {
        if (from == null || to == null) return 0;
        long maxExtracted = StorageUtil.simulateExtract(from, resource, maxAmount, transaction);
        try (Transaction t = Transaction.openNested(transaction)) {
            long accepted = to.insert(resource, maxExtracted, t);
            if (from.extract(resource, accepted, t) == accepted) {
                t.commit();
                return accepted;
            }
        }
        return 0;
    }
    public static <T> Pair<Map<T, Long>, Long> move(@Nullable Storage<T> from, @Nullable Storage<? super T> to, @Nullable TransactionContext transaction) {
        return move(from, to, Long.MAX_VALUE, transaction);
    }
    /**
     @see Storages#move(Storage, Storage, Object, long, TransactionContext)
     */
    public static <T> Pair<Map<T, Long>, Long> move(@Nullable Storage<T> from, @Nullable Storage<? super T> to, @Range(from = 0, to = Long.MAX_VALUE) long maxAmount, @Nullable TransactionContext transaction) {
        if (from == null || to == null) return Pair.of(Map.of(), 0L);
        long movedAmount = 0;
        Map<T, Long> movedResources = new HashMap<>();
        for (StorageView<T> fromView : from.nonEmptyViews()) {
            if (fromView.isResourceBlank()) continue;
            T resource = fromView.getResource();
            long maxExtracted = StorageUtil.simulateExtract(from, resource, maxAmount - movedAmount, transaction);
            try (Transaction t = Transaction.openNested(transaction)) {
                long accepted = to.insert(resource, maxExtracted, t);
                if (from.extract(resource, accepted, t) == accepted) {
                    movedAmount += accepted;
                    movedResources.put(resource, movedResources.getOrDefault(resource, 0L) + accepted);
                    t.commit();
                }
            }
            if (movedAmount >= maxAmount) break;
        }
        return Pair.of(movedResources, movedAmount);
    }
    public static <T> @Unmodifiable Map<T, Long> extractAll(@Nullable Storage<T> storage, @Nullable TransactionContext transaction) {
        if (storage == null) return Map.of();
        Map<T, Long> extracted = new LinkedHashMap<>();
        try (var t = Transaction.openNested(transaction)) {
            for (StorageView<T> view : storage.nonEmptyViews()) {
                T r = view.getResource();
                long a = view.extract(r, view.getAmount(), transaction);
                extracted.put(r, extracted.containsKey(r) ? extracted.get(r) + a : a);
            }
            t.commit();
        }
        return extracted;
    }
    public static <T> Storage<T> combined(Iterable<? extends Storage<T>> storages) {
        var parts = Streams.stream(storages).toList();
        return switch (parts.size()) {
            case 0 -> Storage.empty();
            case 1 -> parts.iterator().next();
            default -> new CombinedStorage<>(parts);
        };
    }
    public static <T> Codec<ResourceAmount<T>> resourceAmountCodec(Codec<T> resourceCodec) {
        return RecordCodecBuilder.create(instance -> instance.group(
          resourceCodec.fieldOf("resource").forGetter(ResourceAmount::resource),
          Codec.LONG.optionalFieldOf("amount", 1L).forGetter(ResourceAmount::amount)
        ).apply(instance, ResourceAmount::new));
    }
    public static <T> ResourceAmount<T> toResourceAmount(StorageView<? extends T> view) {
        return new ResourceAmount<>(view.getResource(), view.getAmount());
    }
    //public static @NotNull NbtCompound toNbt(TransferVariant<?> resource, long amount) {
    //    NbtCompound nbt = new NbtCompound();
    //    if (!resource.isBlank()) {
    //        nbt.put("variant", resource.toNbt());
    //        NamedBinaryTags.put(nbt, "amount", amount, 1);
    //    }
    //    return nbt;
    //}
    private Storages() {throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");}
}
