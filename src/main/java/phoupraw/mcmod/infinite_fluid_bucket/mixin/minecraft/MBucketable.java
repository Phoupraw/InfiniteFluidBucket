package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

@Mixin(Bucketable.class)
interface MBucketable {
    @Redirect(method = "tryBucket", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isAlive()Z"))
    private static boolean infinityBucket(LivingEntity instance, PlayerEntity player, Hand hand, LivingEntity entity) {
        return !InfiniteFluidBucket.isInfinity(player.getStackInHand(hand)) && entity.isAlive();
    }
}
