package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import phoupraw.mcmod.infinite_fluid_bucket.Internals;

@Mixin(InfinityEnchantment.class)
abstract class MInfinityEnchantment extends Enchantment {
    protected MInfinityEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack)
          || stack.isOf(Items.BUCKET) && Internals.CONFIG.instance().emptyBucket
          || stack.isOf(Items.WATER_BUCKET) && Internals.CONFIG.instance().waterBucket
          || stack.isOf(Items.MILK_BUCKET) && Internals.CONFIG.instance().milkBucket
          || stack.isOf(Items.GLASS_BOTTLE) && Internals.CONFIG.instance().glassBottle
          || Internals.isWaterPotion(stack) && Internals.CONFIG.instance().waterPotion;
    }
}
