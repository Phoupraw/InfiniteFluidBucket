package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(MilkBucketItem.class)
abstract class MMilkBucketItem extends Item {
    public MMilkBucketItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return IFBConfig.HANDLER.instance().isMilkBucket();
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return Infinities.isInfinity(stack) && IFBConfig.HANDLER.instance().isMilkBucket() ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @ModifyExpressionValue(method = "finishUsing", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"))
    private boolean checkInf(boolean original, ItemStack stack, World world, LivingEntity user) {
        return original || Infinities.isInfinity(stack) && IFBConfig.HANDLER.instance().isMilkBucket();
    }
}
