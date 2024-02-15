@file:Suppress("UNUSED_ANONYMOUS_PARAMETER")

package phoupraw.mcmod.infinite_fluid_bucket

import net.fabricmc.api.ModInitializer
import net.minecraft.block.DispenserBlock
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import phoupraw.mcmod.infinite_fluid_bucket.misc.InfEmptyBucketBehavior
import phoupraw.mcmod.infinite_fluid_bucket.misc.InfFullBucketBehavior
import phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft.ABucketItem
import phoupraw.mcmod.infinite_fluid_bucket.transfer.InfEmptyBucketStorage
import phoupraw.mcmod.infinite_fluid_bucket.transfer.InfWaterBucketStorage
import phoupraw.mcmod.linked.fabric.transfer.fluid.FluidStorages
import phoupraw.mcmod.linked.lang.MutableEvent

object InfiniteFluidBucket : ModInitializer {
    const val ID = "infinite_fluid_bucket"
    @JvmStatic
    fun isInfinity(fluid: Fluid): Boolean = fluid == Fluids.EMPTY || fluid == Fluids.WATER
    @JvmStatic
    fun isInfinity(itemStack: ItemStack): Boolean = (itemStack.item as? ABucketItem)?.fluid?.let { isInfinity(it) && itemStack.infinity >= 1 } == true
    override fun onInitialize() {
        FluidStorages.ITEM[Items.WATER_BUCKET].addKeyOrder(ID, MutableEvent.DEFAULT_KEY)
        FluidStorages.ITEM[Items.WATER_BUCKET].register(ID) { item, context -> InfWaterBucketStorage.takeIf { isInfinity(context.itemVariant.toStack()) } }
        FluidStorages.ITEM[Items.BUCKET].addKeyOrder(ID, MutableEvent.DEFAULT_KEY)
        FluidStorages.ITEM[Items.BUCKET].register(ID) { item, context -> InfEmptyBucketStorage.takeIf { isInfinity(context.itemVariant.toStack()) } }
        DispenserBlock.registerBehavior(Items.BUCKET, InfEmptyBucketBehavior)
        DispenserBlock.registerBehavior(Items.WATER_BUCKET, InfFullBucketBehavior)
    }
}