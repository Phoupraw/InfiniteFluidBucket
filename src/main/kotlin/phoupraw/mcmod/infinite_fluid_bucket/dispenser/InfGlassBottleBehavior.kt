package phoupraw.mcmod.infinite_fluid_bucket.dispenser

import net.minecraft.block.BeehiveBlock
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.block.entity.BeehiveBlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.potion.PotionUtil
import net.minecraft.potion.Potions
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.FluidTags
import net.minecraft.util.math.BlockPointer
import net.minecraft.world.event.GameEvent
import phoupraw.mcmod.infinite_fluid_bucket.CONFIG
import phoupraw.mcmod.infinite_fluid_bucket.DISPENSER_FALLBACK
import phoupraw.mcmod.infinite_fluid_bucket.infinity

object InfGlassBottleBehavior : FallibleItemDispenserBehavior() {
    public override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
        isSuccess = false
        val serverWorld = pointer.world
        val blockPos = pointer.pos.offset(pointer.state.get(DispenserBlock.FACING))
        val blockState = serverWorld.getBlockState(blockPos)
        if (/*!(stack.infinity && CONFIG.instance().glassBottle) && */blockState.isIn(BlockTags.BEEHIVES) && blockState.contains(BeehiveBlock.HONEY_LEVEL) && blockState.block is BeehiveBlock && blockState.get(BeehiveBlock.HONEY_LEVEL) >= 5) {
            (blockState.block as BeehiveBlock).takeHoney(serverWorld, blockState, blockPos, null, BeehiveBlockEntity.BeeState.BEE_RELEASED)
            this.isSuccess = true
            return this.tryPutFilledBottle(pointer, stack, ItemStack(Items.HONEY_BOTTLE))
        } else if (serverWorld.getFluidState(blockPos).isIn(FluidTags.WATER)) {
            this.isSuccess = true
            return this.tryPutFilledBottle(pointer, stack, PotionUtil.setPotion(ItemStack(Items.POTION), Potions.WATER))
        } else {
            return super.dispenseSilently(pointer, stack)
        }
    }

    fun tryPutFilledBottle(pointer: BlockPointer, emptyBottleStack: ItemStack, filledBottleStack: ItemStack): ItemStack {
        if (isInf(emptyBottleStack)) {
            return emptyBottleStack
        }
        emptyBottleStack.decrement(1)
        if (emptyBottleStack.isEmpty) {
            pointer.world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pointer.pos)
            return filledBottleStack.copy()
        } else {
            if (pointer.blockEntity.addToFirstFreeSlot(filledBottleStack.copy()) < 0) {
                DISPENSER_FALLBACK.dispense(pointer, filledBottleStack.copy())
            }

            return emptyBottleStack
        }
    }
    @JvmStatic
    fun isInf(stack: ItemStack) = stack.isOf(Items.GLASS_BOTTLE) && stack.infinity && CONFIG.instance().glassBottle
}
