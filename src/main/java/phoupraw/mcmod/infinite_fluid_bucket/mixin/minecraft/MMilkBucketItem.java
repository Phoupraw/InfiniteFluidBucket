package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
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
        return Infinities.hasInfinity(stack) && IFBConfig.HANDLER.instance().isMilkBucket() ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @WrapOperation(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrementUnlessCreative(ILnet/minecraft/entity/LivingEntity;)V"))
    private void checkInf(ItemStack instance, int amount, LivingEntity entity, Operation<Void> original) {
        if (!(IFBConfig.HANDLER.instance().isMilkBucket() && Infinities.hasInfinity(instance))) {
            original.call(instance, amount, entity);
        }
    }
}
