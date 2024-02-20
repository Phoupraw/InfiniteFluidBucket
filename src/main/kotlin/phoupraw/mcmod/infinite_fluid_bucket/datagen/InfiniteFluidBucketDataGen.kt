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
import kotlin.reflect.KProperty

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

private val CFG = "yacl3.config.${CONFIG.id()}"
private fun FabricLanguageProvider.TranslationBuilder.add(property: KProperty<*>, name: String, desc: String) {
    val configName = "$CFG.${property.name}"
    add(configName, name)
    add("$configName.desc", desc)
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
            - 玻璃瓶可以附魔无限，可以无限销毁流体。
            - 水瓶可以附魔无限，可以无限给水。
            §l介绍§r
            可以用附魔台或铁砧给桶附魔无限。
            每个物品可以从配置文件或《模组菜单》单独开关。
            可以只安装在服务端，只不过客户端可能有一点同步问题。
            §l你知道吗§r
            本模组灵感来源于《夸克》中的无限水桶。
        """.trimIndent())
        b.add("$CFG.category.${InfiniteFluidBucketConfig.CATEGORY}", "设置")
        b.add(InfiniteFluidBucketConfig::waterBucket, "水桶", """
            启用时，可以用附魔台或铁砧给水桶附魔无限。
            §l无限水桶§r
            - 手持时，可以无限放置水。
            - 手持时，可以无限向炼药锅填充水。
            - 在发射器中，可以无限放置水。
            - 可以向模组的流体储罐无限提供水，每次提供一桶水。
            - 不可以捕鱼。
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::emptyBucket, "空桶", """
            启用时，可以用附魔台或铁砧给铁桶附魔无限。
            §l无限空桶§r
            - 手持时，可以无限舀起并销毁流体源。
            - 手持时，可以无限舀起并销毁炼药锅中的流体和细雪。
            - 在发射器中，可以无限舀起并销毁流体源。
            - 可以被模组的流体储罐无限输入流体，每次可以输入一桶量的流体，并且销毁。
            - 不可以挤奶。
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::milkBucket, "奶桶", """
            启用时，可以用附魔台或铁砧给奶桶附魔无限。
            §l无限奶桶§r
            - 可以无限饮用。
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::glassBottle, "空瓶", """
            启用时，可以用附魔台或铁砧给玻璃瓶附魔无限。
            §l无限空瓶§r
            - 可以无限从炼药锅无限舀起并销毁水。
            - 可以无限收集并销毁龙息。
            - 可以无限从装满蜂蜜的蜂巢和蜂箱收集并销毁蜂蜜。
            - 可以被模组的流体储罐无限输入流体，每次可以输入一瓶量的流体。输入的流体会被销毁。
            - 不能用于合成。
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::waterPotion, "水瓶", """
            启用时，可以用附魔台或铁砧给水瓶附魔无限。
            §l无限水瓶§r
            - 可以无限向炼药锅倒水。
            - 可以无限把泥土变成泥巴。
            - 可以向模组的流体储罐无限提供水，每次提供一瓶水。
            - 不会在合成中消耗。
            - 不能用于酿造。
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
            - Milk bucket can be enchanted with infinity, allowing infintely drinking.
            - Glass bottle can be enchanted with infinity, allowing infinitly discarding fluid.
            - Water potion can be enchanted with infinity, providing infinite water.
            §lProfile§r
            Enchant buckets by enchanting table or anvil.
            Each item can be toggled independently by config file or Mod Menu.
            Can be installed only at server side, just client might have a few sync bugs.
            §lTrivia§r
            This mod is inspired by infinite water bucket of Quark mod.
        """.trimIndent())
        val cfg = "yacl3.config.${CONFIG.id()}"
        b.add("$cfg.category.${InfiniteFluidBucketConfig.CATEGORY}", "Settings")
        b.add(InfiniteFluidBucketConfig::waterBucket, "Water Bucket", """
            When enabled, water bucket can be enchanted with infinity.
            §lInfinity Water Bucket§r
            - Infinitely place water.
            - Infinitely fill cauldron.
            - Infinitely place water in dispenser.
            - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
            - Can't fish.
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::emptyBucket, "Empty Bucket", """
            When enabled, bucket can be enchanted with infinity.
            §lInfinity Empty Bucket§r
            - Infinitely scoop and void fluid.
            - Infinitely empty fluid in cauldron, including powder snow.
            - Infinitely scoop and void fluid in dispenser.
            - Can be inserted with fluid by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
            - Can't milk.
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::milkBucket, "Milk Bucket", """
            When enabled, milk bucket can be enchanted with infinity.
            §lInfinity Milk Bucket§r
            - Can be infinitely drunk.
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::glassBottle, "Empty Bottle", """
            When enabled, glass bottle can be enchanted with infinity.
            §lInfinity Empty Bottle§r
            - Infinitely empty and discard water in cauldron.
            - Infinitely collect and discard dragon breath.
            - Infinitely collect and discard honey from beehive and bee nest with full honey.
            - Can be inserted with fluid by fluid tank of mod. Each time one bottle of fluid. Inserted fluid is voided.
            - Can't be used in crafting.
        """.trimIndent())
        b.add(InfiniteFluidBucketConfig::waterPotion, "Water Potion", """
            When enabled, water potion can be enchanted with infinity.
            §lInfinity Water Potion§r
            - Infinitely fill cauldron.
            - Infinitely convert dirt into mud.
            - Infinitely provide water to fluid tank of mod. Each time one bottle of water.
            - Doesn't consume in crafting.
            - Can't be used in brewing.
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