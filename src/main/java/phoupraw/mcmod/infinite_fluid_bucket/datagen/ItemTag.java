package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

final class ItemTag extends FabricTagProvider.ItemTagProvider {
    public ItemTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        //getOrCreateTagBuilder(IFBItemTags.INFINITABLE).add(Items.BUCKET,Items.WATER_BUCKET,Items.MILK_BUCKET,Items.GLASS_BOTTLE,Items.POTION);
    }
}
