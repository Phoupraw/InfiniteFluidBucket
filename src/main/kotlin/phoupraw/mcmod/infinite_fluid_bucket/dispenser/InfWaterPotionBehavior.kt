package phoupraw.mcmod.infinite_fluid_bucket.dispenser

import net.minecraft.block.Blocks
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.particle.ParticleTypes
import net.minecraft.potion.PotionUtil
import net.minecraft.potion.Potions
import net.minecraft.registry.tag.BlockTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPointer
import net.minecraft.world.event.GameEvent
import phoupraw.mcmod.infinite_fluid_bucket.CONFIG
import phoupraw.mcmod.infinite_fluid_bucket.DISPENSER_FALLBACK
import phoupraw.mcmod.infinite_fluid_bucket.infinity
import phoupraw.mcmod.infinite_fluid_bucket.isWaterPotion

object InfWaterPotionBehavior : ItemDispenserBehavior() {
    @JvmStatic
    fun isInf(stack: ItemStack) = stack.isWaterPotion() && stack.infinity && CONFIG.instance().waterPotion
    public override fun dispenseSilently(pointer: BlockPointer, stack: ItemStack): ItemStack {
        if (PotionUtil.getPotion(stack) !== Potions.WATER) {
            return DISPENSER_FALLBACK.dispense(pointer, stack)
        }
        val serverWorld = pointer.world
        val blockPos = pointer.pos
        val blockPos2 = pointer.pos.offset(pointer.state.get(DispenserBlock.FACING))
        if (!serverWorld.getBlockState(blockPos2).isIn(BlockTags.CONVERTABLE_TO_MUD)) {
            return DISPENSER_FALLBACK.dispense(pointer, stack)
        }
        if (!serverWorld.isClient()) {
            for (i in 0..4) {
                serverWorld.spawnParticles(ParticleTypes.SPLASH, blockPos.x + serverWorld.random.nextDouble(), blockPos.y + 1.0, blockPos.z + serverWorld.random.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0)
            }
        }
        serverWorld.playSound(null, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f)
        serverWorld.emitGameEvent(null, GameEvent.FLUID_PLACE, blockPos)
        serverWorld.setBlockState(blockPos2, Blocks.MUD.defaultState)
        return if (isInf(stack)) stack else ItemStack(Items.GLASS_BOTTLE)
    }
}