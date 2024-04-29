package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.util.concurrent.CompletableFuture;

import static phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.ID;

final class Chinese extends FabricLanguageProvider {
    Chinese(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder b) {
        b.add(InfiniteFluidBucket.NAME, "无限流体桶");
        b.add("modmenu.descriptionTranslation." + ID, """
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
          """);
        b.add("config." + ID + ".waterBucket.desc", """
          启用时，可以用附魔台或铁砧给水桶附魔无限。
          §l无限水桶§r
          - 手持时，可以无限放置水。
          - 手持时，可以无限向炼药锅填充水。
          - 在发射器中，可以无限放置水。
          - 可以向模组的流体储罐无限提供水，每次提供一桶水。
          - 不可以捕鱼。
          """);
        b.add("config." + ID + ".emptyBucket.desc", """
          启用时，可以用附魔台或铁砧给铁桶附魔无限。
          §l无限空桶§r
          - 手持时，可以无限舀起并销毁流体源。
          - 手持时，可以无限舀起并销毁炼药锅中的流体和细雪。
          - 在发射器中，可以无限舀起并销毁流体源。
          - 可以被模组的流体储罐无限输入流体（必须有桶装形式），每次可以输入一桶量的流体，并且销毁。
          - 不可以挤奶。
          """);
        b.add("config." + ID + ".waterPotion.desc", """
          启用时，可以用附魔台或铁砧给水瓶附魔无限。
          §l无限水瓶§r
          - 可以无限向炼药锅倒水。
          - 可以无限把泥土变成泥巴。
          - 可以向模组的流体储罐无限提供水，每次提供一瓶水。
          - 不会在合成中消耗。
          - 不能用于酿造。
          """);
        b.add("config." + ID + ".glassBottle.desc", """
          启用时，可以用附魔台或铁砧给玻璃瓶附魔无限。
          §l无限空瓶§r
          - 可以无限从炼药锅无限舀起并销毁水。
          - 可以无限收集并销毁龙息。
          - 可以无限从装满蜂蜜的蜂巢和蜂箱收集并销毁蜂蜜。
          - 可以被模组的流体储罐无限输入流体（必须有瓶装形式），每次可以输入一瓶量的流体。输入的流体会被销毁。
          - 不能用于合成。
          """);
        b.add("config." + ID + ".milkBucket.desc", """
          启用时，可以用附魔台或铁砧给奶桶附魔无限。
          §l无限奶桶§r
          - 可以无限饮用。
          """);
    }
}
