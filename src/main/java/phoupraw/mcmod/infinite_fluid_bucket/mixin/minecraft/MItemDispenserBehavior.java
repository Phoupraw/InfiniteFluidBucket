package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(ItemDispenserBehavior.class)
abstract class MItemDispenserBehavior implements DispenserBehavior {
    /**
     防止无限容器们数量减一或被替换成无附魔空容器，对水桶、熔岩桶、空桶、空瓶（包括装水和装蜂蜜）、水瓶有效。
     */
    @Inject(method = "decrementStackWithRemainder", at = @At("HEAD"), cancellable = true)
    private void checkInf(BlockPointer pointer, ItemStack stack, ItemStack remainder, CallbackInfoReturnable<ItemStack> cir) {
        if (Infinities.isInfinity(stack)) {
            cir.setReturnValue(stack);
        }
    }
}
