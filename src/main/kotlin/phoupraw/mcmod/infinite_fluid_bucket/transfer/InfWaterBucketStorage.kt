package phoupraw.mcmod.infinite_fluid_bucket.transfer

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.minecraft.fluid.Fluids

object InfWaterBucketStorage : InfFluidBucketStorage(FluidVariant.of(Fluids.WATER), FluidConstants.BUCKET)