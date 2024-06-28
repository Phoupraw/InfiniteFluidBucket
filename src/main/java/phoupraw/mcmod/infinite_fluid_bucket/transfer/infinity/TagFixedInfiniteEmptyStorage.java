package phoupraw.mcmod.infinite_fluid_bucket.transfer.infinity;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

public class TagFixedInfiniteEmptyStorage extends FixedInfiniteEmptyStorage {
    public final TagKey<Fluid> insertable;
    public TagFixedInfiniteEmptyStorage(ContainerItemContext context, long capacity, TagKey<Fluid> insertable) {
        super(context, capacity);
        this.insertable = insertable;
    }
    @Override
    protected boolean canInsert(FluidVariant resource) {
        return super.canInsert(resource) && (Registries.FLUID.getOrCreateEntryList(insertable).size() == 0 || InfiniteFluidBucket.isIn(resource, insertable));
    }
}
