package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

public sealed interface IFBItems permits IFBConstants {
    ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, IFBIDs.IFB, FabricItemGroup.builder()
      .displayName(InfiniteFluidBucket.name())
      .icon(IFBItems::icon)
      .entries(IFBItems::entries)
      .build());
    private static void entries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        RegistryEntry<Enchantment> infinity = Infinities.getInfinity(displayContext.lookup());
        if (IFBConfig.getConfig().isEmptyBucket()) {
            entries.add(Infinities.addInfinity(Items.BUCKET.getDefaultStack(), infinity));
        }
        if (IFBConfig.getConfig().isWaterBucket()) {
            entries.add(Infinities.addInfinity(Items.WATER_BUCKET.getDefaultStack(), infinity));
        }
        if (IFBConfig.isLavaBucket()) {
            entries.add(Infinities.addInfinity(Items.LAVA_BUCKET.getDefaultStack(), infinity));
        }
        if (IFBConfig.getConfig().isMilkBucket()) {
            entries.add(Infinities.addInfinity(Items.MILK_BUCKET.getDefaultStack(), infinity));
        }
        if (IFBConfig.getConfig().isGlassBottle()) {
            entries.add(Infinities.addInfinity(Items.GLASS_BOTTLE.getDefaultStack(), infinity));
        }
        if (IFBConfig.getConfig().isWaterPotion()) {
            entries.add(Infinities.addInfinity(PotionContentsComponent.createStack(Items.POTION, Potions.WATER), infinity));
        }
        if (IFBConfig.isHoneyBottle()) {
            entries.add(Infinities.addInfinity(Items.HONEY_BOTTLE.getDefaultStack(), infinity));
        }
    }
    private static ItemStack icon() {
        ItemStack stack = Items.WATER_BUCKET.getDefaultStack();
        stack.set(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, false);
        return stack;
    }
}
