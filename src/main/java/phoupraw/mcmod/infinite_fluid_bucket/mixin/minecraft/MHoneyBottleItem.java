package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(HoneyBottleItem.class)
abstract class MHoneyBottleItem {
    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void captureParam(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir, @Share(InfiniteFluidBucket.ID + ":stack") LocalRef<ItemStack> stack0) {
        stack0.set(stack.copy());
    }
    @Inject(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"), cancellable = true)
    private void checkInf(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir, @Share(InfiniteFluidBucket.ID + ":stack") LocalRef<ItemStack> stack0) {
        if (Infinities.isInfinity(stack0.get())) {
            stack.decrementUnlessCreative(-1, user);
            cir.setReturnValue(stack);
        }
    }
}
