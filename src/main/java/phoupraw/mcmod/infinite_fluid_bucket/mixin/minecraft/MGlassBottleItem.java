package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.Internals;
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfGlassBottleBehavior;

@Mixin(GlassBottleItem.class)
abstract class MGlassBottleItem extends Item {
    public MGlassBottleItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return Internals.CONFIG.instance().glassBottle;
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return InfGlassBottleBehavior.isInf(stack) ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @WrapOperation(method = "fill", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack checkInf(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, Operation<ItemStack> original) {
        return InfGlassBottleBehavior.isInf(inputStack) ? inputStack : original.call(inputStack, player, outputStack);
    }
}
