package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BucketItem.class)
public interface ABucketItem {
    @Accessor
    Fluid getFluid();
    @Invoker
    void invokePlayEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos);
}
