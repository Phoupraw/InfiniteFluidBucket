package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfEmptyBucketBehavior;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfGlassBottleBehavior;

@Mixin(value = Ingredient.class, priority = 1100)
//@Debug(export = true)
abstract class MIngredient {
    @Inject(method = "test(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void checkInf(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (InfEmptyBucketBehavior.isInf(itemStack) || InfGlassBottleBehavior.isInf(itemStack)) {
            cir.setReturnValue(false);
        }
    }
}
