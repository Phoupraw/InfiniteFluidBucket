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
        b.add("modmenu.descriptionTranslation.$ID", """
            给桶附魔无限！
            §l概要§r
            - 水桶可以附魔无限，可以无限倒水。
            - 铁桶可以附魔无限，可以无限销毁流体。
            §l介绍§r
            可以用附魔台或铁砧给水桶和空桶附魔无限。
            附魔了无限的水桶不能捕鱼。
            支持发射器和使用Fabric Transfer API的所有模组。
            §l你知道吗§r
            本模组灵感来源于《夸克》中的无限水桶。
        """.trimIndent())
    }
}

private class EnglishGen(dataOutput: FabricDataOutput) : FabricLanguageProvider(dataOutput) {
    override fun generateTranslations(b: TranslationBuilder) {
        b.add("modmenu.nameTranslation.$ID", "Infinite Fluid Bucket")
        b.add("modmenu.descriptionTranslation.$ID", """
            Enchant bucket with infinity!
            §lSynopsis§r
            - Water bucket can be enchanted with infinity, allowing infinite water.
            - Bucket can be enchanted with infinity, allowing infinitly discarding fluid.
            §lProfile§r
            Enchant buckets by enchanting table or anvil.
            Water bucket with infinity can't capture fish.
            Support dispenser and all mods uses Fabric Transfer API.
            §lTrivia§r
            This mod is inspired by infinite water bucket of Quark mod.
        """.trimIndent())
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