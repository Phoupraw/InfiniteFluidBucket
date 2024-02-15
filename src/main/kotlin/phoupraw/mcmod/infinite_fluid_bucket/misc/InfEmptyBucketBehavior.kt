package phoupraw.mcmod.infinite_fluid_bucket.misc

import net.minecraft.block.DispenserBlock
import net.minecraft.block.FluidDrainable
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.block.entity.DispenserBlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPointer
import net.minecraft.world.WorldAccess
import net.minecraft.world.event.GameEvent
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.isInfinity

object InfEmptyBucketBehavior : ItemDispenserBehavior() {
    @JvmField
    val FALLBACK: DispenserBehavior = ItemDispenserBehavior()
    override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
        val world: WorldAccess = pointer.world
        val blockPos = pointer.pos.offset(pointer.blockState.get(DispenserBlock.FACING))
        val blockState = world.getBlockState(blockPos)
        val block = blockState.block
        if (block !is FluidDrainable) {
            return super.dispenseSilently(pointer, stack)
        }
        val fullStack: ItemStack = block.tryDrainFluid(world, blockPos, blockState)
        if (fullStack.isEmpty) {
            return super.dispenseSilently(pointer, stack)
        }
        world.emitGameEvent(null, GameEvent.FLUID_PICKUP, blockPos)
        if (isInfinity(stack)) return stack
        val newStack = fullStack.item.defaultStack
        stack.decrement(1)
        if (stack.isEmpty) {
            return newStack
        }
        if (pointer.getBlockEntity<DispenserBlockEntity>().addToFirstFreeSlot(newStack) < 0) {
            FALLBACK.dispense(pointer, newStack)
        }
        return stack
    }
}
