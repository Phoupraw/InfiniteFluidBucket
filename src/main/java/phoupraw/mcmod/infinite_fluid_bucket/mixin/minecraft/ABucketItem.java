package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BucketItem.class)
public interface ABucketItem {
    @Accessor
    Fluid getFluid();
}
