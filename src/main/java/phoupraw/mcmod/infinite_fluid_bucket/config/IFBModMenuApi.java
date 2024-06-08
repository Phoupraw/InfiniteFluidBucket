package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.terraformersmc.modmenu.api.ModMenuApi;

public final class IFBModMenuApi implements ModMenuApi {
    //private static YetAnotherConfigLib.Builder modifyBuilder(IFBConfig defaults, IFBConfig config, YetAnotherConfigLib.Builder builder) {
    //    return builder
    //      .screenInit(IFBModMenuApi::onScreenInit)
    //      .title(Text.translatable(InfiniteFluidBucket.NAME))
    //      .category(ConfigCategory
    //        .createBuilder()
    //        .name(Text.empty())
    //        .option(Option
    //          .<Boolean>createBuilder()
    //          .name(Items.WATER_BUCKET.getName())
    //          .description(OptionDescription
    //            .createBuilder()
    //            .customImage(ItemRendererInConfig.of(Infinities.LateInitItemStacks.WATER_BUCKET))
    //            .text(Text.translatable("config." + ID + ".waterBucket.desc"))
    //            .build())
    //          .binding(defaults.isWaterBucket(), config::isWaterBucket, config::setWaterBucket)
    //          .controller(TickBoxControllerBuilder::create)
    //          .build())
    //        .option(Option
    //          .<Boolean>createBuilder()
    //          .name(Items.BUCKET.getName())
    //          .description(OptionDescription
    //            .createBuilder()
    //            .customImage(ItemRendererInConfig.of(Infinities.LateInitItemStacks.EMTPY_BUCKET))
    //            .text(Text.translatable("config." + ID + ".emptyBucket.desc"))
    //            .build())
    //          .binding(defaults.isEmptyBucket(), config::isEmptyBucket, config::setEmptyBucket)
    //          .controller(TickBoxControllerBuilder::create)
    //          .build())
    //        .option(Option
    //          .<Boolean>createBuilder()
    //          .name(Misc.WATER_POTION.getName())
    //          .description(OptionDescription
    //            .createBuilder()
    //            .customImage(ItemRendererInConfig.of(Infinities.LateInitItemStacks.WATER_BOTTLE))
    //            .text(Text.translatable("config." + ID + ".waterPotion.desc"))
    //            .build())
    //          .binding(defaults.isWaterPotion(), config::isWaterPotion, config::setWaterPotion)
    //          .controller(TickBoxControllerBuilder::create)
    //          .build())
    //        .option(Option
    //          .<Boolean>createBuilder()
    //          .name(Items.GLASS_BOTTLE.getName())
    //          .description(OptionDescription
    //            .createBuilder()
    //            .customImage(ItemRendererInConfig.of(Infinities.LateInitItemStacks.EMPTY_BOTTLE))
    //            .text(Text.translatable("config." + ID + ".glassBottle.desc"))
    //            .build())
    //          .binding(defaults.isGlassBottle(), config::isGlassBottle, config::setGlassBottle)
    //          .controller(TickBoxControllerBuilder::create)
    //          .build())
    //        .option(Option
    //          .<Boolean>createBuilder()
    //          .name(Items.MILK_BUCKET.getName())
    //          .description(OptionDescription
    //            .createBuilder()
    //            .customImage(ItemRendererInConfig.of(Infinities.LateInitItemStacks.MILK_BUCKET))
    //            .text(Text.translatable("config." + ID + ".milkBucket.desc"))
    //            .build())
    //          .binding(defaults.isMilkBucket(), config::isMilkBucket, config::setMilkBucket)
    //          .controller(TickBoxControllerBuilder::create)
    //          .build())
    //        .build());
    //}
    //private static void onScreenInit(YACLScreen screen) {HANDLER.load();}
    //@Override
    //public ConfigScreenFactory<?> getModConfigScreenFactory() {
    //    return parent -> YetAnotherConfigLib.create(HANDLER, IFBModMenuApi::modifyBuilder).generateScreen(parent);
    //}
}
