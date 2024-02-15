package phoupraw.mcmod.infinite_fluid_bucket.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import phoupraw.mcmod.infinite_fluid_bucket.ID
import java.util.concurrent.CompletableFuture

@Suppress("unused")
object TrifleStorageDataGen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(g: FabricDataGenerator) {
        val pack = g.createPack()
        pack.addProvider(::ChineseGen)
        pack.addProvider(::EnglishGen)
        pack.addProvider(::FluidTagGen)
        pack.addProvider(::ItemTagGen)
    }
}

private class ChineseGen(dataOutput: FabricDataOutput) : FabricLanguageProvider(dataOutput, "zh_cn") {
    override fun generateTranslations(b: TranslationBuilder) {
        b.add("modmenu.nameTranslation.$ID", "无限流体桶")
        b.add("modmenu.descriptionTranslation.$ID", "TODO")
    }
}

private class EnglishGen(dataOutput: FabricDataOutput) : FabricLanguageProvider(dataOutput) {
    override fun generateTranslations(b: TranslationBuilder) {
        b.add("modmenu.nameTranslation.$ID", "Infinite Fluid Bucket")
        b.add("modmenu.descriptionTranslation.$ID", "TODO")
    }
}

private class FluidTagGen(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.FluidTagProvider(output, completableFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        //getOrCreateTagBuilder(InfiniteFluidBucket.INFINITE).add(Fluids.EMPTY, Fluids.WATER)
    }
}

private class ItemTagGen(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, completableFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        //getOrCreateTagBuilder(InfiniteFluidBucket.INFINITE).add(Items.BUCKET, Items.GLASS_BOTTLE, Items.WATER_BUCKET)
    }
}