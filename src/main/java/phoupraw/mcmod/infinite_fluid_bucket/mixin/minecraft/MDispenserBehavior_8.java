package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

/**
 {@link Items#WATER_BUCKET}，防止放置流体时替换成空桶
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$15")
abstract class MDispenserBehavior_8 {
    @ModifyReturnValue(method = "dispenseSilently", at = @At(value = "RETURN"))
    private ItemStack checkInfinity(ItemStack original, BlockPointer pointer, ItemStack stack) {
        return stack.isOf(Items.WATER_BUCKET) && IFBConfig.getConfig().isWaterBucket() && Infinities.hasInfinity(stack, pointer.world().getRegistryManager()) ? stack : original;
    }
}
