package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(Bucketable.class)
interface MBucketable {
    @ModifyExpressionValue(method = "tryBucket", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isAlive()Z"))
    private static boolean infinityBucket(boolean original, PlayerEntity player, Hand hand, LivingEntity entity) {
        ItemStack stack = player.getStackInHand(hand);
        return original && !(stack.isOf(Items.WATER_BUCKET) && IFBConfig.HANDLER.instance().isWaterBucket() && Infinities.hasInfinity(stack));
    }
}
