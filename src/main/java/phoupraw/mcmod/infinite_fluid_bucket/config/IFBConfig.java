package phoupraw.mcmod.infinite_fluid_bucket.config;

import lombok.Getter;
import lombok.Setter;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;

@Getter
@Setter
public class IFBConfig {
    //public static final Map<Item, BooleanSupplier> ITEM_OPTIONS = Map.of(
    //  Items.WATER_BUCKET, getConfig()::isWaterBucket,
    //  Items.BUCKET, getConfig()::isEmptyBucket,
    //  Items.GLASS_BOTTLE, getConfig()::isGlassBottle,
    //  Items.MILK_BUCKET, getConfig()::isMilkBucket
    //);
    private static IFBConfig config;
    public static boolean isLavaBucket() {
        return IFBGameRules.VALUES.getBoolean(IFBGameRules.LAVA_BUCKET);
    }
    public static boolean isHoneyBottle() {
        return IFBGameRules.VALUES.getBoolean(IFBGameRules.HONEY_BOTTLE);
    }
    public static IFBConfig getConfig() {
        //if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
        //    return YACL.HANDLER.instance();
        //}
        if (config == null) {
            config = new GameRuleDelegate();
        }
        return config;
    }
    public static void setConfig(IFBConfig config) {
        IFBConfig.config = config;
    }
    //@SerialEntry
    public boolean waterBucket = true;
    //@SerialEntry
    public boolean emptyBucket = true;
    //@SerialEntry
    public boolean waterPotion = true;
    //@SerialEntry
    public boolean glassBottle = true;
    //@SerialEntry
    public boolean milkBucket = true;
    
    //@UtilityClass
    //static class YACL {
    //    public static final ConfigClassHandler<IFBConfig> HANDLER = ConfigClassHandler
    //      .createBuilder(IFBConfig.class)
    //      .id(IFBIDs.of("only"))
    //      .serializer(handler -> GsonConfigSerializerBuilder
    //        .create(handler)
    //        .setJson5(true)
    //        .setPath(FabricLoader.getInstance().getConfigDir().resolve(InfiniteFluidBucket.ID + ".cfg.json5"))
    //        .build())
    //      .build();
    //    static {
    //        YACL.HANDLER.load();
    //    }
    //}
    
    static class GameRuleDelegate extends IFBConfig {
        @Override
        public boolean isWaterBucket() {
            return IFBGameRules.VALUES.getBoolean(IFBGameRules.WATER_BUCKET);
        }
        @Override
        public boolean isEmptyBucket() {
            return IFBGameRules.VALUES.getBoolean(IFBGameRules.EMPTY_BUCKET);
        }
        @Override
        public boolean isWaterPotion() {
            return IFBGameRules.VALUES.getBoolean(IFBGameRules.WATER_POTION);
        }
        @Override
        public boolean isGlassBottle() {
            return IFBGameRules.VALUES.getBoolean(IFBGameRules.GLASS_BOTTLE);
        }
        @Override
        public boolean isMilkBucket() {
            return IFBGameRules.VALUES.getBoolean(IFBGameRules.MILK_BUCKET);
        }
    }
}
