package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfGlassBottleBehavior;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfWaterPotionBehavior;

@Mixin(CauldronBehavior.class)
interface MCauldronBehavior {
    @WrapOperation(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack checkInf(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, Operation<ItemStack> original) {
        return InfiniteFluidBucket.isInfinity(inputStack) || InfGlassBottleBehavior.isInf(inputStack) || InfWaterPotionBehavior.isInf(inputStack) ? inputStack : original.call(inputStack, player, outputStack);
    }
}
