package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.config.TipDialogScreen;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBEnchantmentTags;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBFluidTags;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBItemTags;

import java.util.concurrent.CompletableFuture;

import static phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.ID;

final class Chinese extends FabricLanguageProvider {
    Chinese(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder b) {
        String modName = "无限流体桶";
        String modded = modName + "：";
        b.add(InfiniteFluidBucket.NAME_KEY, modName);
        b.add("modmenu.descriptionTranslation." + ID, """
          给桶附魔无限！
          §l概要§r
          - 铁桶可以附魔无限，可以无限销毁流体。
          - 水桶可以附魔无限，可以无限倒水。
          - 奶桶可以附魔无限，可以无限饮用。
          - 玻璃瓶可以附魔无限，可以无限销毁流体。
          - 水瓶可以附魔无限，可以无限给水。
          - 可选的无限熔岩桶和无限蜂蜜瓶。
          - 自定义任意无限流体物品。
          §l介绍§r
          可以用附魔台或铁砧给桶附魔无限。
          每个物品可以从配置文件或《模组菜单》单独开关。
          可以只安装在服务端，只不过客户端可能有一点同步问题。
          §l你知道吗§r
          本模组灵感来源于《夸克》中的无限水桶。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.WATER_BUCKET, "无限水桶", """
          启用时，可以用附魔台或铁砧给水桶附魔无限。
          §l无限水桶§r
          - 手持时，可以无限放置水。
          - 手持时，可以无限向炼药锅填充水。
          - 在发射器中，可以无限放置水。
          - 可以向模组的流体储罐无限提供水，每次提供一桶水。
          - 不会在合成中消耗。
          - 不可以捕鱼。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.EMPTY_BUCKET, "无限空桶", """
          启用时，可以用附魔台或铁砧给铁桶附魔无限。
          §l无限空桶§r
          - 手持时，可以无限舀起并销毁流体源。
          - 手持时，可以无限舀起并销毁炼药锅中的流体和细雪。
          - 在发射器中，可以无限舀起并销毁流体源。
          - 可以被模组的流体储罐无限输入流体（必须有桶装形式），每次可以输入一桶量的流体，并且销毁。
          - 不能用于合成。
          - 不可以挤奶。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.WATER_POTION, "无限水瓶", """
          启用时，可以用附魔台或铁砧给水瓶附魔无限。
          §l无限水瓶§r
          - 可以无限向炼药锅倒水。
          - 可以无限把泥土变成泥巴。
          - 可以向模组的流体储罐无限提供水，每次提供一瓶水。
          - 不会在合成中消耗。
          - 不能用于酿造。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.GLASS_BOTTLE, "无限空瓶", """
          启用时，可以用附魔台或铁砧给玻璃瓶附魔无限。
          §l无限空瓶§r
          - 可以无限从炼药锅无限舀起并销毁水。
          - 可以无限收集并销毁龙息。
          - 可以无限从装满蜂蜜的蜂巢和蜂箱收集并销毁蜂蜜。
          - 可以被模组的流体储罐无限输入流体（必须有瓶装形式），每次可以输入一瓶量的流体。输入的流体会被销毁。
          - 不能用于合成。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.MILK_BUCKET, "无限奶桶", """
          启用时，可以用附魔台或铁砧给奶桶附魔无限。
          §l无限奶桶§r
          - 可以无限饮用。
          - 不会在合成中消耗。
          - 如果有模组为奶桶添加了流体，则无限奶桶可以无限提供流体。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.LAVA_BUCKET, "无限熔岩桶", """
          启用时，可以用附魔台或铁砧给熔岩桶附魔无限。
          §l无限熔岩桶§r
          - 手持时，可以无限放置熔岩。
          - 手持时，可以无限向炼药锅填充熔岩。
          - 在发射器中，可以无限放置熔岩。
          - 可以向模组的流体储罐无限提供熔岩，每次提供一桶。
          - 不会在合成中消耗。
          - 可以在熔炉中充当无限燃料。
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.HONEY_BOTTLE, "无限蜂蜜瓶", """
          启用时，可以用附魔台或铁砧给蜂蜜瓶附魔无限。
          §l无限蜂蜜瓶§r
          - 可以无限饮用。
          - 不会在合成中消耗。
          - 如果有模组为蜂蜜瓶添加了流体，则无限蜂蜜瓶可以无限提供流体。
          """);
        b.add(IFBItemTags.INSERTABLE, modded + "无限空容器");
        b.add(IFBItemTags.EXTRACTABLE, modded + "无限满容器");
        b.add(IFBFluidTags.INSERTABLE, modded + "可以向无限空容器注入的流体");
        b.add(IFBFluidTags.EXTRACTABLE, modded + "可以从无限满容器抽取的流体");
        b.add(IFBFluidTags.BUCKET, modded + "可以向无限空桶注入的流体");
        b.add(IFBFluidTags.GLASS_BOTTLE, modded + "可以向无限玻璃瓶注入的流体");
        b.add(IFBEnchantmentTags.INFINITIER, modded + "拥有此标签中任意附魔的物品可以提供无限流体");
        b.add(TipDialogScreen.NO_WORLD, "进入世界后才可以查看或修改游戏规则的值，现在只能查看游戏规则的描述。");
        b.add(TipDialogScreen.NO_PERMISSION, "你没有权限修改游戏规则的值，你只能查看游戏规则的值和描述。");
    }
}
