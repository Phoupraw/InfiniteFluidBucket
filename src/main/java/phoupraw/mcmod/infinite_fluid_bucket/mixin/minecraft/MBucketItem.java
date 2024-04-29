package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(BucketItem.class)
class MBucketItem extends Item {
    public MBucketItem(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isEnchantable(ItemStack stack) {
        if (stack.isOf(Items.WATER_BUCKET)) {
            return IFBConfig.HANDLER.instance().isWaterBucket();
        }
        if (stack.isOf(Items.BUCKET)) {
            return IFBConfig.HANDLER.instance().isEmptyBucket();
        }
        if (stack.isOf(Items.MILK_BUCKET)) {
            return IFBConfig.HANDLER.instance().isMilkBucket();
        }
        return false;
    }
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return Infinities.isInfinityFluidBucket(stack) ? stack.copy() : super.getRecipeRemainder(stack);
    }
    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsage;exchangeStack(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack infinityBucket(ItemStack inputStack, PlayerEntity player, ItemStack outputStack) {
        return Infinities.isInfinityFluidBucket(inputStack) ? inputStack : ItemUsage.exchangeStack(inputStack, player, outputStack);
    }
    @Redirect(method = "getEmptiedStack", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"))
    private static boolean infinityBucket(PlayerAbilities instance, ItemStack stack, PlayerEntity player) {
        return player.isCreative() || Infinities.isInfinityFluidBucket(stack);
    }
}
