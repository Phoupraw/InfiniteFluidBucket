package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBEnchantmentTags;

import java.util.concurrent.CompletableFuture;

final class EnchTag extends FabricTagProvider.EnchantmentTagProvider {
    public EnchTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(IFBEnchantmentTags.INFINITIER).add(Enchantments.INFINITY);
    }
}
