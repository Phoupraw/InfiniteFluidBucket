package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(CauldronBehavior.class)
interface MCauldronBehavior {
    @WrapOperation(method = {"emptyCauldron", "fillCauldron", "method_32222", "method_32220", "method_32219"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack checkInf(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, Operation<ItemStack> original) {
        return Infinities.isInfinity(inputStack) ? inputStack : original.call(inputStack, player, outputStack);
    }
}
