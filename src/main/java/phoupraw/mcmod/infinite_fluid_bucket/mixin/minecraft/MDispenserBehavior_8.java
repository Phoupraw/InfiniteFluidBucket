package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

/**
 {@link Items#WATER_BUCKET}，防止放置流体时替换成空桶
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$15")
abstract class MDispenserBehavior_8 {
    @Inject(method = "dispenseSilently", at = @At(value = "RETURN"), cancellable = true)
    private void checkInfinity(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.isOf(Items.WATER_BUCKET) && IFBConfig.HANDLER.instance().isWaterBucket() && Infinities.hasInfinity(stack)) {
            cir.setReturnValue(stack);
        }
    }
}
