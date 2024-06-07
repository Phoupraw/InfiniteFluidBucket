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
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.util.function.Predicate;

import static phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig.YACL.HANDLER;

@UtilityClass
public class Infinities {
    public static final ItemStack WATER_BUCKET = addInfinity(Items.WATER_BUCKET.getDefaultStack());
    public static final ItemStack EMTPY_BUCKET = addInfinity(Items.BUCKET.getDefaultStack());
    public static final ItemStack WATER_BOTTLE = addInfinity(Misc.WATER_POTION.copy());
    public static final ItemStack EMPTY_BOTTLE = addInfinity(Items.GLASS_BOTTLE.getDefaultStack());
    public static final ItemStack MILK_BUCKET = addInfinity(Items.MILK_BUCKET.getDefaultStack());
    private static @Nullable MinecraftServer server;
    public static RegistryEntry<Enchantment> infinity() {
        return getRegistries().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.INFINITY).orElseThrow();
    }
    public static boolean hasInfinity(ItemStack any) {
        return EnchantmentHelper.getLevel(infinity(), any) > 0;
    }
    public static boolean isInfinityFluidBucket(ItemStack any) {
        return (any.isOf(Items.WATER_BUCKET) && HANDLER.instance().isWaterBucket()
          || canInfinityEmptyBucket(any)
          /*|| any.isOf(Items.MILK_BUCKET) && IFBConfig.HANDLER.instance().isMilkBucket()*/
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
    @Contract("_ -> param1")
    public static @NotNull ItemStack addInfinity(ItemStack any) {
        any.addEnchantment(infinity(), 1);
        return any;
    }
    @Contract("_ -> param1")
    public static ItemStack removeInfinity(ItemStack any) {
        ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(any);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(enchantments);
        builder.remove(Predicate.isEqual(Enchantments.INFINITY));
        EnchantmentHelper.set(any, builder.build());
        return any;
    }
    public static @Nullable MinecraftServer getServer() {
        return server;
    }
    public static void setServer(@Nullable MinecraftServer server) {
        if ((server == null) != (Infinities.server == null)) {
            InfiniteFluidBucket.LOGGER.throwing(new IllegalStateException("The game progress has two servers in the meantime! param: %s, field: %s".formatted(server, Infinities.server)));
        }
        Infinities.server = server;
    }
    public static void unsetServer(MinecraftServer server) {
        if (server != Infinities.server) {
            InfiniteFluidBucket.LOGGER.throwing(new IllegalArgumentException("The param server isn't the field server! param: %s, field: %s".formatted(server, Infinities.server)));
        }
        Infinities.setServer(null);
    }
    public static DynamicRegistryManager getRegistries() {
        MinecraftServer server = getServer();
        if (server != null) {
            return server.getRegistryManager();
        }
        InfiniteFluidBucket.LOGGER.throwing(new IllegalStateException("Try getting DynamicRegistryManager without a running server!"));
        return DynamicRegistryManager.EMPTY;
    }
}
