package phoupraw.mcmod.infinite_fluid_bucket.misc;

import lombok.experimental.UtilityClass;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBEnchantmentTags;

@UtilityClass
public class Infinities {
    ///**
    // 时间复杂度：常数
    // */
    @ApiStatus.Obsolete
    public static boolean hasInfinity(ItemStack any, RegistryWrapper.WrapperLookup lookup) {
        return hasInfinity(any);//EnchantmentHelper.getLevel(getInfinity(lookup), any) > 0;
    }
    ///**
    // 应优先使用{@link #hasInfinity(ItemStack, RegistryWrapper.WrapperLookup)}。
    // <br/>
    // 时间复杂度：与物品拥有的附魔数量成线性。
    // */
    public static boolean hasInfinity(ItemStack any) {
        return EnchantmentHelper.hasAnyEnchantmentsIn(any, IFBEnchantmentTags.INFINITIER);
    }
    public static boolean canInfinity(ItemStack any) {
        return canInfinityEmpty(any)
          || any.isOf(Items.MILK_BUCKET) && IFBConfig.getConfig().isMilkBucket()
          || any.isOf(Items.POTION) && canPotionInfinity(any)
          || any.isOf(Items.LAVA_BUCKET) && IFBConfig.isLavaBucket()
          || any.isOf(Items.HONEY_BOTTLE) && IFBConfig.isHoneyBottle();
    }
    public static boolean isInfinityFluidBucket(ItemStack any) {
        return (any.isOf(Items.WATER_BUCKET) && IFBConfig.getConfig().isWaterBucket()
          || canInfinityEmptyBucket(any)
          /*|| any.isOf(Items.MILK_BUCKET) && IFBConfig.IFBConfig.getConfig().isMilkBucket()*/
        ) && hasInfinity(any);
    }
    public static boolean isPotionWater(ItemStack potion) {
        return potion.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).potion().orElse(null) == Potions.WATER;
    }
    public static boolean isPotionInfinity(ItemStack potion) {
        return canPotionInfinity(potion) && hasInfinity(potion);
    }
    public static boolean isInfinityPotion(ItemStack any) {
        return any.isOf(Items.POTION) && isPotionInfinity(any);
    }
    public static boolean isGlassBottleInfinity(ItemStack glassBottle) {
        return IFBConfig.getConfig().isGlassBottle() && hasInfinity(glassBottle);
    }
    public static boolean isInfinityGlassBottle(ItemStack any) {
        return any.isOf(Items.GLASS_BOTTLE) && isGlassBottleInfinity(any);
    }
    public static boolean isInfinityEmpty(ItemStack any) {
        return canInfinityEmpty(any) && hasInfinity(any);
    }
    public static boolean canInfinityBucket(ItemStack any) {
        return any.isOf(Items.WATER_BUCKET) && IFBConfig.getConfig().isWaterBucket() || canInfinityEmptyBucket(any);
    }
    public static boolean canInfinityEmpty(ItemStack any) {
        return canInfinityEmptyBucket(any) || canInfinityGlassBottle(any);
    }
    public static boolean canPotionInfinity(ItemStack potion) {
        return IFBConfig.getConfig().isWaterPotion() && isPotionWater(potion);
    }
    public static boolean canInfinityEmptyBucket(ItemStack any) {
        return any.isOf(Items.BUCKET) && IFBConfig.getConfig().isEmptyBucket();
    }
    public static boolean canInfinityGlassBottle(ItemStack any) {
        return any.isOf(Items.GLASS_BOTTLE) && IFBConfig.getConfig().isGlassBottle();
    }
    public static @NotNull ItemStack addInfinity(ItemStack any, RegistryWrapper.WrapperLookup lookup) {
        return addInfinity(any, getInfinity(lookup));
    }
    public static boolean isInfinity(ItemStack any) {
        return canInfinity(any) && hasInfinity(any);
    }
    public static @NotNull ItemStack addInfinity(ItemStack any, RegistryEntry<Enchantment> infinity) {
        any.addEnchantment(infinity, 1);
        return any;
    }
    //public static RegistryEntry<Enchantment> getInfinity() {
    //    return getInfinity(getRegistries());//getRegistries().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.INFINITY).orElseThrow();
    //}
    public static RegistryEntry<Enchantment> getInfinity(RegistryWrapper.WrapperLookup lookup) {
        RegistryWrapper.Impl<Enchantment> wrapper = lookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        for (RegistryEntry<Enchantment> entry : wrapper.getOrThrow(IFBEnchantmentTags.INFINITIER)) {
            return entry;
        }
        return wrapper.getOrThrow(Enchantments.INFINITY);
    }
    @Contract("_ -> param1")
    public static ItemStack removeInfinity(ItemStack any) {
        ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(any);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(enchantments);
        builder.remove(Infinities::isInfinity);
        EnchantmentHelper.set(any, builder.build());
        return any;
    }
    public static boolean isInfinity(RegistryEntry<Enchantment> entry) {
        return entry.isIn(IFBEnchantmentTags.INFINITIER);
    }
}
