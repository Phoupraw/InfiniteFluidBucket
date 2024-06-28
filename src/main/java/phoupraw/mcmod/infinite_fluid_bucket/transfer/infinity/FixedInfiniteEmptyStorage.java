package phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantItemStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

public class FixedInfiniteEmptyStorage extends SingleVariantItemStorage<FluidVariant> {
    public final long capacity;
    public FixedInfiniteEmptyStorage(ContainerItemContext context, long capacity) {
        super(context);
        this.capacity = capacity;
    }
    @Override
    protected FluidVariant getBlankResource() {
        return FluidVariant.blank();
    }
    @Override
    protected FluidVariant getResource(ItemVariant currentVariant) {
        return getBlankResource();
    }
    @Override
    protected long getAmount(ItemVariant currentVariant) {
        return 0;
    }
    @Override
    protected long getCapacity(FluidVariant variant) {
        return capacity;
    }
    @Override
    protected ItemVariant getUpdatedVariant(ItemVariant currentVariant, FluidVariant newResource, long newAmount) {
        return currentVariant;
    }
    @Override
    public long insert(FluidVariant insertedResource, long maxAmount, TransactionContext transaction) {
        return maxAmount >= capacity ? super.insert(insertedResource, maxAmount, transaction) : 0;
    }
    @Override
    public boolean supportsExtraction() {
        return false;
    }
}
