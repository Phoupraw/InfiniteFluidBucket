package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
    //@Override
    //public boolean isEnchantable(ItemStack stack) {
    //    return IFBConfig.getConfig().isMilkBucket();
    //}
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return Infinities.hasInfinity(stack) && IFBConfig.getConfig().isMilkBucket() ? stack.copy() : super.getRecipeRemainder(stack);
    }
    /**
     防止无限奶桶数量减一
     */
    @WrapWithCondition(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrementUnlessCreative(ILnet/minecraft/entity/LivingEntity;)V"))
    private boolean checkInf(ItemStack instance, int amount, LivingEntity entity) {
        return !Infinities.isInfinity(instance);
    }
    /**
     防止无限奶桶被替换成空桶
     */
    @WrapOperation(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/item/ItemStack;"))
    private ItemStack checkInf(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, boolean creativeOverride, Operation<ItemStack> original) {
        return Infinities.isInfinity(inputStack) ? inputStack : original.call(inputStack, player, outputStack, creativeOverride);
    }
}
