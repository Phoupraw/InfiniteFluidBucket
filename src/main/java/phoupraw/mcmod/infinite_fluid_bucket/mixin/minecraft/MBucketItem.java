package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(BucketItem.class)
class MBucketItem extends Item {
    @ModifyExpressionValue(method = "getEmptiedStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isInCreativeMode()Z"))
    private static boolean infinityBucket(boolean original, ItemStack stack, PlayerEntity player) {
        return original || Infinities.canInfinityBucket(stack) && Infinities.hasInfinity(stack, player.getRegistryManager());
    }
    public MBucketItem(Settings settings) {
        super(settings);
    }
    //@Override
    //public boolean isEnchantable(ItemStack stack) {
    //    if (stack.isOf(Items.WATER_BUCKET)) {
    //        return IFBConfig.getConfig().isWaterBucket();
    //    }
    //    if (stack.isOf(Items.BUCKET)) {
    //        return IFBConfig.getConfig().isEmptyBucket();
    //    }
    //    if (stack.isOf(Items.MILK_BUCKET)) {
    //        return IFBConfig.getConfig().isMilkBucket();
    //    }
    //    return false;
    //}
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return Infinities.isInfinityFluidBucket(stack) ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack infinityBucket(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, Operation<ItemStack> original) {
        return Infinities.canInfinityBucket(inputStack) && Infinities.hasInfinity(inputStack, player.getRegistryManager()) ? inputStack : original.call(inputStack, player, outputStack);
    }
}
