package phoupraw.mcmod.infinite_fluid_bucket.misc;

import lombok.experimental.UtilityClass;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

import java.util.Map;

import static phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig.HANDLER;

@UtilityClass
public class Infinities {
    public static final ItemStack WATER_BUCKET = addInfinity(Items.WATER_BUCKET.getDefaultStack());
    public static final ItemStack EMTPY_BUCKET = addInfinity(Items.BUCKET.getDefaultStack());
    public static final ItemStack WATER_BOTTLE = addInfinity(Misc.WATER_POTION.copy());
    public static final ItemStack EMPTY_BOTTLE = addInfinity(Items.GLASS_BOTTLE.getDefaultStack());
    public static final ItemStack MILK_BUCKET = addInfinity(Items.MILK_BUCKET.getDefaultStack());
    public static boolean hasInfinity(ItemStack any) {
        return EnchantmentHelper.getLevel(Enchantments.INFINITY, any) > 0;
    }
    public static boolean isInfinityFluidBucket(ItemStack any) {
        return (any.isOf(Items.WATER_BUCKET) && HANDLER.instance().isWaterBucket()
          || canInfinityEmptyBucket(any)
          /*|| any.isOf(Items.MILK_BUCKET) && IFBConfig.HANDLER.instance().isMilkBucket()*/
        ) && hasInfinity(any);
    }
    public static boolean isPotionWater(ItemStack potion) {
        return PotionUtil.getPotion(potion) == Potions.WATER;
    }
    public static boolean isPotionInfinity(ItemStack potion) {
        return canPotionInfinity(potion) && hasInfinity(potion);
    }
    public static boolean isInfinityPotion(ItemStack any) {
        return any.isOf(Items.POTION) && isPotionInfinity(any);
    }
    public static boolean isGlassBottleInfinity(ItemStack glassBottle) {
        return HANDLER.instance().isGlassBottle() && hasInfinity(glassBottle);
    }
    public static boolean isInfinityGlassBottle(ItemStack any) {
        return any.isOf(Items.GLASS_BOTTLE) && isGlassBottleInfinity(any);
    }
    public static boolean isInfinityEmpty(ItemStack any) {
        return (canInfinityEmptyBucket(any) || canInfinityGlassBottle(any)) && hasInfinity(any);
    }
    public static boolean canPotionInfinity(ItemStack potion) {
        return HANDLER.instance().isWaterPotion() && isPotionWater(potion);
    }
    public static boolean canInfinityEmptyBucket(ItemStack any) {
        return any.isOf(Items.BUCKET) && HANDLER.instance().isEmptyBucket();
    }
    public static boolean canInfinityGlassBottle(ItemStack any) {
        return any.isOf(Items.GLASS_BOTTLE) && HANDLER.instance().isGlassBottle();
    }
    public static boolean canInfinity(ItemStack any) {
        return canInfinityEmptyBucket(any)
          || any.isOf(Items.WATER_BUCKET) && HANDLER.instance().isWaterBucket()
          || any.isOf(Items.MILK_BUCKET) && HANDLER.instance().isMilkBucket()
          || canInfinityGlassBottle(any)
          || any.isOf(Items.POTION) && canPotionInfinity(any);
    }
    public static boolean isInfinity(ItemStack any) {
        return canInfinity(any) && hasInfinity(any);
    }
    public static ItemStack addInfinity(ItemStack any) {
        any.addEnchantment(Enchantments.INFINITY, 1);
        return any;
    }
    public static ItemStack removeInfinity(ItemStack any) {
        Map<Enchantment, Integer> map = EnchantmentHelper.get(any);
        map.remove(Enchantments.INFINITY);
        EnchantmentHelper.set(map, any);
        return any;
    }
}
