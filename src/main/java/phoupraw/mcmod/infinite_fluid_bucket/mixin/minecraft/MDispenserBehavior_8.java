package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

/**
 {@link Items#WATER_BUCKET}
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$8")
abstract class MDispenserBehavior_8 {
    @Inject(method = "dispenseSilently", at = @At(value = "RETURN"), cancellable = true)
    private void checkInfinity(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (Infinities.isInfinityFluidBucket(stack)) {
            cir.setReturnValue(stack);
        }
    }
}
