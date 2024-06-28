package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(value = FabricItemStack.class, remap = false)
interface MFabricItemStack {
    @SuppressWarnings({"DataFlowIssue", "UnreachableCode"})
    @Inject(method = "getRecipeRemainder", at = @At("HEAD"), cancellable = true)
    private void checkInf(CallbackInfoReturnable<ItemStack> cir) {
        if (Infinities.isInfinity((ItemStack) (Object) this)) {
            cir.setReturnValue(((ItemStack) (Object) this).copy());
        }
    }
}
