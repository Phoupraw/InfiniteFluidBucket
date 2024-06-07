package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

public sealed interface IFBItems permits InterfaceFinalizer {
    ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, IFBIDs.of(InfiniteFluidBucket.ID), FabricItemGroup.builder()
      .displayName(Text.translatable(InfiniteFluidBucket.NAME))
      .icon(() -> Infinities.LateInitItemStacks.WATER_BUCKET)
      .entries(IFBItems::entries)
      .build());
    private static void entries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries) {
        RegistryEntry<Enchantment> infinity = Infinities.getInfinity(displayContext.lookup());
        if (IFBConfig.getConfig().isEmptyBucket()) {
            ItemStack stack = Items.BUCKET.getDefaultStack();
            stack.addEnchantment(infinity, 1);
            entries.add(stack);
        }
        if (IFBConfig.getConfig().isWaterBucket()) {
            ItemStack stack = Items.WATER_BUCKET.getDefaultStack();
            stack.addEnchantment(infinity, 1);
            entries.add(stack);
        }
        if (IFBConfig.getConfig().isMilkBucket()) {
            ItemStack stack = Items.MILK_BUCKET.getDefaultStack();
            stack.addEnchantment(infinity, 1);
            entries.add(stack);
        }
        if (IFBConfig.getConfig().isGlassBottle()) {
            ItemStack stack = Items.GLASS_BOTTLE.getDefaultStack();
            stack.addEnchantment(infinity, 1);
            entries.add(stack);
        }
        if (IFBConfig.getConfig().isWaterPotion()) {
            ItemStack stack = PotionContentsComponent.createStack(Items.POTION, Potions.WATER);
            stack.addEnchantment(infinity, 1);
            entries.add(stack);
        }
    }
}
