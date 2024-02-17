package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfEmptyBucketBehavior;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfGlassBottleBehavior;

@Mixin(Ingredient.class)
abstract class MIngredient {
    @WrapOperation(method = "test(Lnet/minecraft/item/ItemStack;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean checkInf(ItemStack instance, Item item, Operation<Boolean> original, ItemStack itemStack) {
        return original.call(instance, item) && !InfEmptyBucketBehavior.isInf(itemStack) && !InfGlassBottleBehavior.isInf(itemStack);
    }
}
