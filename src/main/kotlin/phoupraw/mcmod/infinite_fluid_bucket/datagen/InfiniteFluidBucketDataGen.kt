package phoupraw.mcmod.infinite_fluid_bucket.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import phoupraw.mcmod.infinite_fluid_bucket.CONFIG
import phoupraw.mcmod.infinite_fluid_bucket.ID
import phoupraw.mcmod.infinite_fluid_bucket.config.InfiniteFluidBucketConfig
import java.util.concurrent.CompletableFuture

@Suppress("unused")
object InfiniteFluidBucketDataGen : DataGeneratorEntrypoint {
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
            - 水桶可以附魔无限，可以无限倒水。
            - 铁桶可以附魔无限，可以无限销毁流体。
            - 奶桶可以附魔无限，可以无限饮用。
            §l介绍§r
            可以用附魔台或铁砧给桶附魔无限。
            每个物品可以从配置文件或《模组菜单》单独开关。
            无限水桶
            - 可以无限放置水。
            - 可以无限向炼药锅填充水。
            - 在发射器中，可以无限放置水。
            - 可以向模组的流体储罐无限提供水，每次提供一桶水。
            - 不可以捕鱼。
            无限铁桶
            - 可以无限舀起并销毁流体源。
            - 可以无限清空炼药锅中的流体和细雪。
            - 在发射器中，可以无限舀起并销毁流体源。
            - 可以被模组的流体储罐无限输入流体，每次可以输入一桶量的流体。输入的流体会被销毁。
            - 不可以挤奶。
            无限奶桶
            - 可以无限饮用。
            §l你知道吗§r
            本模组灵感来源于《夸克》中的无限水桶。
        """.trimIndent())
        val cfg = "yacl3.config.${CONFIG.id()}"
        b.add("$cfg.category.${InfiniteFluidBucketConfig.CATEGORY}", "设置")
        b.add("$cfg.${InfiniteFluidBucketConfig::waterBucket.name}", "水桶")
        b.add("$cfg.${InfiniteFluidBucketConfig::waterBucket.name}.desc", """
            启用时，可以用附魔台或铁砧给水桶附魔无限。
            §l无限水桶§r
            - 手持时，可以无限放置水。
            - 手持时，可以无限向炼药锅填充水。
            - 在发射器中，可以无限放置水。
            - 可以向模组的流体储罐无限提供水，每次提供一桶水。
            - 不可以捕鱼。
        """.trimIndent())
        b.add("$cfg.${InfiniteFluidBucketConfig::emptyBucket.name}", "空桶")
        b.add("$cfg.${InfiniteFluidBucketConfig::emptyBucket.name}.desc", """
            启用时，可以用附魔台或铁砧给铁桶附魔无限。
            §l无限空桶§r
            - 手持时，可以无限舀起并销毁流体源。
            - 手持时，可以无限舀起并销毁炼药锅中的流体和细雪。
            - 在发射器中，可以无限舀起并销毁流体源。
            - 可以被模组的流体储罐无限输入流体，每次可以输入一桶量的流体，并且销毁。
            - 不可以挤奶。
        """.trimIndent())
        b.add("$cfg.${InfiniteFluidBucketConfig::milkBucket.name}", "奶桶")
        b.add("$cfg.${InfiniteFluidBucketConfig::milkBucket.name}.desc", """
            启用时，可以用附魔台或铁砧给奶桶附魔无限。
            §l无限奶桶§r
            - 可以无限饮用。
        """.trimIndent())
    }
}

private class EnglishGen(dataOutput: FabricDataOutput) : FabricLanguageProvider(dataOutput) {
    override fun generateTranslations(b: TranslationBuilder) {
        b.add("modmenu.nameTranslation.$ID", "Infinite Fluid Bucket")
        b.add("modmenu.descriptionTranslation.$ID", """
            Enchant bucket with infinity!
            §lSynopsis§r
            - Water bucket can be enchanted with infinity, allowing infinite water.
            - Bucket can be enchanted with infinity, allowing infinitly discarding fluid.
            §lProfile§r
            Enchant buckets by enchanting table or anvil.
            Each item can be toggled independently by config file or Mod Menu.
            Infinity Water Bucket
            - Infinitely place water.
            - Infinitely fill cauldron.
            - Infinitely place water in dispenser.
            - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
            - Can't fish.
            Infinity Empty Bucket
            - Infinitely scoop and void fluid.
            - Infinitely empty fluid in cauldron, including powder snow.
            - Infinitely scoop and void fluid in dispenser.
            - Can be inserted with fluid by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
            - Can't milk.
            Infinity Milk Bucket
            - Can be infinitely drunk.
            §lTrivia§r
            This mod is inspired by infinite water bucket of Quark mod.
        """.trimIndent())
        val cfg = "yacl3.config.${CONFIG.id()}"
        b.add("$cfg.category.${InfiniteFluidBucketConfig.CATEGORY}", "Settings")
        b.add("$cfg.${InfiniteFluidBucketConfig::waterBucket.name}", "Water Bucket")
        b.add("$cfg.${InfiniteFluidBucketConfig::waterBucket.name}.desc", """
            When enabled, water bucket can be enchanted with infinity.
            §lInfinity Water Bucket§r
            - Infinitely place water.
            - Infinitely fill cauldron.
            - Infinitely place water in dispenser.
            - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
            - Can't fish.
        """.trimIndent())
        b.add("$cfg.${InfiniteFluidBucketConfig::emptyBucket.name}", "Empty Bucket")
        b.add("$cfg.${InfiniteFluidBucketConfig::emptyBucket.name}.desc", """
            When enabled, bucket can be enchanted with infinity.
            §lInfinity Empty Bucket§r
            - Infinitely scoop and void fluid.
            - Infinitely empty fluid in cauldron, including powder snow.
            - Infinitely scoop and void fluid in dispenser.
            - Can be inserted with fluid by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
            - Can't milk.
        """.trimIndent())
        b.add("$cfg.${InfiniteFluidBucketConfig::milkBucket.name}", "Milk Bucket")
        b.add("$cfg.${InfiniteFluidBucketConfig::milkBucket.name}.desc", """
            When enabled, milk bucket can be enchanted with infinity.
            §lInfinity Milk Bucket§r
            - Can be infinitely drunk.
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