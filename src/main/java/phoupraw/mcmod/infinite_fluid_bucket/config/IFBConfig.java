package phoupraw.mcmod.infinite_fluid_bucket.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import lombok.Getter;
import lombok.Setter;
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
        HANDLER.load();
    }
    public static final Map<Item, BooleanSupplier> ITEM_OPTIONS = Map.of(
      Items.WATER_BUCKET, HANDLER.instance()::isWaterBucket,
      Items.BUCKET, HANDLER.instance()::isEmptyBucket,
      Items.GLASS_BOTTLE, HANDLER.instance()::isGlassBottle,
      Items.MILK_BUCKET, HANDLER.instance()::isMilkBucket
    );
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
}
