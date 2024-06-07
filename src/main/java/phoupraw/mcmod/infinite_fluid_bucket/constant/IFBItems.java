package phoupraw.mcmod.infinite_fluid_bucket.constant;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

public sealed interface IFBItems permits InterfaceFinalizer {
    ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, IFBIDs.of(InfiniteFluidBucket.ID), FabricItemGroup.builder()
      .displayName(Text.translatable(InfiniteFluidBucket.NAME))
      .icon(Suppliers.ofInstance(Infinities.WATER_BUCKET))
      .entries(IFBItems::entries)
      .build());
    private static void entries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        if (IFBConfig.getConfig().isEmptyBucket()) {
            entries.add(Infinities.EMTPY_BUCKET);
        }
        if (IFBConfig.getConfig().isWaterBucket()) {
            entries.add(Infinities.WATER_BUCKET);
        }
        if (IFBConfig.getConfig().isMilkBucket()) {
            entries.add(Infinities.MILK_BUCKET);
        }
        if (IFBConfig.getConfig().isGlassBottle()) {
            entries.add(Infinities.EMPTY_BOTTLE);
        }
        if (IFBConfig.getConfig().isWaterPotion()) {
            entries.add(Infinities.WATER_BOTTLE);
        }
    }
}
