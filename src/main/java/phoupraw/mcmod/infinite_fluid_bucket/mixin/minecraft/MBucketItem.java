package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

@Mixin(BucketItem.class)
class MBucketItem extends Item {
    @Shadow
    @Final
    private Fluid fluid;
    public MBucketItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return InfiniteFluidBucket.isInfinity(fluid);
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return InfiniteFluidBucket.isInfinity(stack) ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack infinityBucket(ItemStack inputStack, PlayerEntity player, ItemStack outputStack) {
        return InfiniteFluidBucket.isInfinity(inputStack) ? inputStack : ItemUsage.exchangeStack(inputStack, player, outputStack);
    }
    @Redirect(method = "getEmptiedStack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"))
    private static boolean infinityBucket(PlayerAbilities instance, ItemStack stack, PlayerEntity player) {
        return player.isCreative() || InfiniteFluidBucket.isInfinity(stack);
    }
}
