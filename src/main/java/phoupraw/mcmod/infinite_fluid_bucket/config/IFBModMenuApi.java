package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.gui.YACLScreen;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Misc;

import static phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.ID;
import static phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig.HANDLER;

public final class IFBModMenuApi implements ModMenuApi {
    private static YetAnotherConfigLib.Builder modifyBuilder(IFBConfig defaults, IFBConfig config, YetAnotherConfigLib.Builder builder) {
        return builder
          .screenInit(IFBModMenuApi::onScreenInit)
          .title(Text.translatable(InfiniteFluidBucket.NAME))
          .category(ConfigCategory
            .createBuilder()
            .name(Text.empty())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.WATER_BUCKET.getName())
              .description(OptionDescription
                .createBuilder()
                .customImage(ItemRendererInConfig.of(Infinities.WATER_BUCKET))
                .text(Text.translatable("config." + ID + ".waterBucket.desc"))
                .build())
              .binding(HANDLER.defaults().isWaterBucket(), HANDLER.instance()::isWaterBucket, HANDLER.instance()::setWaterBucket)
              .controller(TickBoxControllerBuilder::create)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.BUCKET.getName())
              .description(OptionDescription
                .createBuilder()
                .customImage(ItemRendererInConfig.of(Infinities.EMTPY_BUCKET))
                .text(Text.translatable("config." + ID + ".emptyBucket.desc"))
                .build())
              .binding(HANDLER.defaults().isEmptyBucket(), HANDLER.instance()::isEmptyBucket, HANDLER.instance()::setEmptyBucket)
              .controller(TickBoxControllerBuilder::create)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Misc.WATER_POTION.getName())
              .description(OptionDescription
                .createBuilder()
                .customImage(ItemRendererInConfig.of(Infinities.WATER_BOTTLE))
                .text(Text.translatable("config." + ID + ".waterPotion.desc"))
                .build())
              .binding(HANDLER.defaults().isWaterPotion(), HANDLER.instance()::isWaterPotion, HANDLER.instance()::setWaterPotion)
              .controller(TickBoxControllerBuilder::create)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.GLASS_BOTTLE.getName())
              .description(OptionDescription
                .createBuilder()
                .customImage(ItemRendererInConfig.of(Infinities.EMPTY_BOTTLE))
                .text(Text.translatable("config." + ID + ".glassBottle.desc"))
                .build())
              .binding(HANDLER.defaults().isGlassBottle(), HANDLER.instance()::isGlassBottle, HANDLER.instance()::setGlassBottle)
              .controller(TickBoxControllerBuilder::create)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.MILK_BUCKET.getName())
              .description(OptionDescription
                .createBuilder()
                .customImage(ItemRendererInConfig.of(Infinities.MILK_BUCKET))
                .text(Text.translatable("config." + ID + ".milkBucket.desc"))
                .build())
              .binding(HANDLER.defaults().isMilkBucket(), HANDLER.instance()::isMilkBucket, HANDLER.instance()::setMilkBucket)
              .controller(TickBoxControllerBuilder::create)
              .build())
            .build());
    }
    private static void onScreenInit(YACLScreen screen) {HANDLER.load();}
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.create(HANDLER, IFBModMenuApi::modifyBuilder).generateScreen(parent);
    }
}
