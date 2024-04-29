package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

/**
 {@link Items#POTION}
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$20")
abstract class MDispenserBehavior_20 {
    @ModifyExpressionValue(method = "dispenseSilently", at = @At(value = "NEW", target = "(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack checkInf(ItemStack original, BlockPointer pointer, ItemStack stack) {
        return Infinities.isPotionInfinity(stack) ? stack : original;
    }
}
