package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import static phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig.HANDLER;

public final class IFBModMenuApi implements ModMenuApi {
    private static YetAnotherConfigLib.Builder modifyBuilder(IFBConfig defaults, IFBConfig config, YetAnotherConfigLib.Builder builder) {
        return builder
          .screenInit(screen -> HANDLER.load())
          .title(Text.translatable(InfiniteFluidBucket.NAME))
          .category(ConfigCategory
            .createBuilder()
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.WATER_BUCKET.getName())
              .binding(HANDLER.defaults().isWaterBucket(), HANDLER.instance()::isWaterBucket, HANDLER.instance()::setWaterBucket)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.BUCKET.getName())
              .binding(HANDLER.defaults().isEmptyBucket(), HANDLER.instance()::isEmptyBucket, HANDLER.instance()::setEmptyBucket)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(PotionUtil.setPotion(Items.POTION.getDefaultStack(), Potions.WATER).getName())
              .binding(HANDLER.defaults().isWaterPotion(), HANDLER.instance()::isWaterPotion, HANDLER.instance()::setWaterPotion)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.GLASS_BOTTLE.getName())
              .binding(HANDLER.defaults().isGlassBottle(), HANDLER.instance()::isGlassBottle, HANDLER.instance()::setGlassBottle)
              .build())
            .option(Option
              .<Boolean>createBuilder()
              .name(Items.MILK_BUCKET.getName())
              .binding(HANDLER.defaults().isMilkBucket(), HANDLER.instance()::isMilkBucket, HANDLER.instance()::setMilkBucket)
              .build())
            .build());
    }
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.create(HANDLER, IFBModMenuApi::modifyBuilder).generateScreen(parent);
    }
}
