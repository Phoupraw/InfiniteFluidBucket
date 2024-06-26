package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(BrewingStandBlockEntity.class)
abstract class MBrewingStandBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
    protected MBrewingStandBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }
    @ModifyExpressionValue(method = "isValid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private boolean checkInf(boolean original, int slot, ItemStack stack) {
        World world = getWorld();
        return original && !(stack.isOf(Items.POTION) && Infinities.canPotionInfinity(stack) && (world == null ? Infinities.hasInfinity(stack) : Infinities.hasInfinity(stack, world.getRegistryManager())));
    }
}
