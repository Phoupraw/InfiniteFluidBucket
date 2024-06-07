package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(PotionItem.class)
abstract class MPotionItem extends Item {
    public MPotionItem(Settings settings) {
        super(settings);
    }
    //@Override
    //public boolean isEnchantable(ItemStack stack) {
    //    return Infinities.canPotionInfinity(stack);
    //}
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return Infinities.isPotionInfinity(stack) ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @WrapWithCondition(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrementUnlessCreative(ILnet/minecraft/entity/LivingEntity;)V"))
    private boolean checkInf(ItemStack instance, int amount, LivingEntity entity) {
        return !(Infinities.canPotionInfinity(instance) && Infinities.hasInfinity(instance, entity.getRegistryManager()));
    }
    @ModifyExpressionValue(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isInCreativeMode()Z"))
    private boolean checkInf(boolean original, ItemStack stack, World world, LivingEntity user) {
        return original || Infinities.canPotionInfinity(stack) && Infinities.hasInfinity(stack, world.getRegistryManager());
    }
    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack checkInf(ItemStack inputStack, PlayerEntity player, ItemStack outputStack, Operation<ItemStack> original) {
        return Infinities.canPotionInfinity(inputStack) && Infinities.hasInfinity(inputStack, player.getRegistryManager()) ? inputStack : original.call(inputStack, player, outputStack);
    }
}
