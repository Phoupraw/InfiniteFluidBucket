package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

import java.util.function.Predicate;

/**
 {@link Items#GLASS_BOTTLE}，防止从蜂箱装蜂蜜，防止从水源装水时替换成水瓶。
 */
@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$6")
abstract class MDispenserBehavior_17 {
    @WrapOperation(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;Ljava/util/function/Predicate;)Z"))
    private boolean checkInf(BlockState instance, TagKey<Block> tagKey, Predicate<BlockState> predicate, Operation<Boolean> original, BlockPointer pointer, ItemStack stack) {
        return !(IFBConfig.getConfig().isGlassBottle() && Infinities.hasInfinity(stack, pointer.world().getRegistryManager())) && original.call(instance, tagKey, predicate);
    }
    @Inject(method = "replace", at = @At("HEAD"), cancellable = true)
    private void checkInf(BlockPointer pointer, ItemStack emptyBottleStack, ItemStack filledBottleStack, CallbackInfoReturnable<ItemStack> cir) {
        if (IFBConfig.getConfig().isGlassBottle() && Infinities.hasInfinity(emptyBottleStack, pointer.world().getRegistryManager())) {
            cir.setReturnValue(emptyBottleStack);
        }
    }
}
