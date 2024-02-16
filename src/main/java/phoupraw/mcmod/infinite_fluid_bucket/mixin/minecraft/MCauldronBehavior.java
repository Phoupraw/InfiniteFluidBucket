package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.util.function.Predicate;

@Mixin(CauldronBehavior.class)
interface MCauldronBehavior {
    @WrapOperation(method = "fillCauldron", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"))
    private static void checkInf(PlayerEntity instance, Hand argHand, ItemStack argStack, Operation<Void> original, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
        if (!InfiniteFluidBucket.isInfinity(stack)) {
            original.call(instance, argHand, argStack);
        }
    }
    @WrapOperation(method = "emptyCauldron", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"))
    private static void checkInf(PlayerEntity instance, Hand argHand, ItemStack argStack, Operation<Void> original, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, ItemStack output, Predicate<BlockState> fullPredicate, SoundEvent soundEvent) {
        if (!InfiniteFluidBucket.isInfinity(stack)) {
            original.call(instance, argHand, argStack);
        }
    }
}
