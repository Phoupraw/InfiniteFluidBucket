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
 {@link Items#BUCKET}
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$9")
abstract class MDispenserBehavior_9 {
    @Inject(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"), cancellable = true)
    private void checkInf(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (IFBConfig.HANDLER.instance().isEmptyBucket() && Infinities.hasInfinity(stack)) {
            cir.setReturnValue(stack);
        }
    }
}
