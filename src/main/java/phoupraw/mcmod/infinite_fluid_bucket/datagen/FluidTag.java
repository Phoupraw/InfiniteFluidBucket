package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

final class FluidTag extends FabricTagProvider.FluidTagProvider {
    public FluidTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //getOrCreateTagBuilder(IFBFluidTags.INFINITABLE).add(Fluids.WATER,Fluids.EMPTY);
    }
}
