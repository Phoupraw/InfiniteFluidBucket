package phoupraw.mcmod.infinite_fluid_bucket.misc

import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.item.FluidModificationItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket

class InfFullBucketBehavior : ItemDispenserBehavior() {
    override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
        val item = stack.item
        if (item !is FluidModificationItem) return stack
        val blockPos: BlockPos = pointer.pos.offset(pointer.blockState[DispenserBlock.FACING])
        val world: World = pointer.world
        if (!item.placeFluid(null, world, blockPos, null)) {
            return InfEmptyBucketBehavior.FALLBACK.dispense(pointer, stack)
        }
        item.onEmptied(null, world, stack, blockPos)
        return if (InfiniteFluidBucket.isInfinity(stack)) stack else Items.BUCKET.defaultStack
    }
}