package phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantItemStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

public class FixedInfiniteFluidStorage extends SingleVariantItemStorage<FluidVariant> {
    public final FluidVariant resource;
    public final long amount;
    public FixedInfiniteFluidStorage(ContainerItemContext context, FluidVariant resource, long amount) {
        super(context);
        this.resource = resource;
        this.amount = amount;
    }
    @Override
    protected FluidVariant getBlankResource() {
        return FluidVariant.blank();
    }
    @Override
    protected FluidVariant getResource(ItemVariant currentVariant) {
        return resource;
    }
    @Override
    protected long getAmount(ItemVariant currentVariant) {
        return amount;
    }
    @Override
    protected long getCapacity(FluidVariant variant) {
        return amount;
    }
    @Override
    protected ItemVariant getUpdatedVariant(ItemVariant currentVariant, FluidVariant newResource, long newAmount) {
        return currentVariant;
    }
    @Override
    public boolean supportsInsertion() {
        return false;
    }
    @Override
    public long extract(FluidVariant extractedResource, long maxAmount, TransactionContext transaction) {
        return maxAmount >= amount ? super.extract(extractedResource, maxAmount, transaction) : 0;
    }
}
