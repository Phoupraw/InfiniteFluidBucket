package phoupraw.mcmod.infinite_fluid_bucket.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBIDs;

import java.util.Map;
import java.util.function.BooleanSupplier;

@Getter
@Setter
public final class IFBConfig {
    public static final Map<Item, BooleanSupplier> ITEM_OPTIONS = Map.of(
      Items.WATER_BUCKET, getConfig()::isWaterBucket,
      Items.BUCKET, getConfig()::isEmptyBucket,
      Items.GLASS_BOTTLE, getConfig()::isGlassBottle,
      Items.MILK_BUCKET, getConfig()::isMilkBucket
    );
    private static IFBConfig config = new IFBConfig();
    public static IFBConfig getConfig() {
        if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
            return YACL.HANDLER.instance();
        }
        if (config == null) {
            config = new IFBConfig();
        }
        return config;
    }
    public static void setConfig(IFBConfig config) {
        IFBConfig.config = config;
    }
    @SerialEntry
    public boolean waterBucket = true;
    @SerialEntry
    public boolean emptyBucket = true;
    @SerialEntry
    public boolean waterPotion = true;
    @SerialEntry
    public boolean glassBottle = true;
    @SerialEntry
    public boolean milkBucket = true;
    
    @UtilityClass
    class YACL {
        public static final ConfigClassHandler<IFBConfig> HANDLER = ConfigClassHandler
          .createBuilder(IFBConfig.class)
          .id(IFBIDs.of("only"))
          .serializer(handler -> GsonConfigSerializerBuilder
            .create(handler)
            .setJson5(true)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve(InfiniteFluidBucket.ID + ".cfg.json5"))
            .build())
          .build();
        static {
            YACL.HANDLER.load();
        }
    }
}
