package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapelessRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(ShapelessRecipe.class)
abstract class MShapelessRecipe {
    @ModifyExpressionValue(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/RecipeInputInventory;getStack(I)Lnet/minecraft/item/ItemStack;"))
    private ItemStack checkInf(ItemStack original) {
        return Infinities.isInfinityEmpty(original) ? ItemStack.EMPTY : original;
    }
}
