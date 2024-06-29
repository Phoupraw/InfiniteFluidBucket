package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.GameRules;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;

public final class IFBModMenuApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            GameRules instance = new GameRulesInstance();
            for (var entry : IFBGameRules.KEYS.entrySet()) {
                String name = entry.getKey();
                GameRules.Key<GameRules.BooleanRule> key = entry.getValue();
                instance.get(key).set(IFBGameRules.VALUES.getBoolean(name), null);
            }
            //Timer timer = new Timer();
            EditGameRulesScreen screen = new EditGameRulesScreen(instance, gameRules0 -> {
                //timer.cancel();
                ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
                if (gameRules0.isPresent() && networkHandler != null) {
                    GameRules gameRules = gameRules0.get();
                    for (var entry : IFBGameRules.KEYS.entrySet()) {
                        String name = entry.getKey();
                        boolean value = gameRules.get(entry.getValue()).get();
                        if (value != IFBGameRules.VALUES.getBoolean(name)) {
                            //IFBGameRules.VALUES.put(name,value);
                            networkHandler.sendCommand("gamerule " + name + " " + value);
                        }
                    }
                    
                } /*else{
                    MinecraftClient.getInstance().setScreen(new ParentedMessageScreen(Text.translatable(NO_WORLD_TIP),parent));
                }*/
                MinecraftClient.getInstance().setScreen(parent);
            });
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player == null) {
                return new TipDialogScreen(parent, screen, TipDialogScreen.NO_WORLD);
                //    timer.schedule(new TimerTask() {
                //        final Random random = new Random();
                //        @Override
                //        public void run() {
                //            if (MinecraftClient.getInstance().getNetworkHandler() == null) {
                //                for (Element screenChild : screen.children()) {
                //                    if (screenChild instanceof EditGameRulesScreen.RuleListWidget listWidget) {
                //                        for (EditGameRulesScreen.AbstractRuleWidget ruleWidget : listWidget.children()) {
                //                            if (ruleWidget instanceof EditGameRulesScreen.BooleanRuleWidget) {
                //                                var booleanRuleWidget = (EditGameRulesScreen.BooleanRuleWidget & AEditGameRulesScreen_BooleanRuleWidget) ruleWidget;
                //                                booleanRuleWidget.getToggleButton().setValue(random.nextBoolean());
                //                            }
                //                        }
                //                    }
                //                }
                //                //System.out.println(screen.children());
                //                //for (var key : IFBGameRules.KEYS.values()) {
                //                //    instance.get(key).set(random.nextBoolean(), null);
                //                //}
                //            }
                //        }
                //    }, 100, 100);
            } else if (!player.hasPermissionLevel(2)) {
                return new TipDialogScreen(parent, screen, TipDialogScreen.NO_PERMISSION);
            }
            return screen;
        };
    }
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
