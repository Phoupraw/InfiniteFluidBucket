package phoupraw.mcmod.infinite_fluid_bucket.cauldron

import net.minecraft.block.BlockState
import net.minecraft.block.LeveledCauldronBlock
import net.minecraft.block.cauldron.CauldronBehavior
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.item.Items
import net.minecraft.potion.PotionUtil
import net.minecraft.potion.Potions
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import phoupraw.mcmod.infinite_fluid_bucket.dispenser.InfGlassBottleBehavior

object WaterCauldronGlassBottleBehavior : CauldronBehavior {
    override fun interact(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, stack: ItemStack): ActionResult {
        if (!world.isClient()) {
            val item = stack.item
            if (!InfGlassBottleBehavior.isInf(stack)) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, PotionUtil.setPotion(ItemStack(Items.POTION), Potions.WATER)))
            }
            player.incrementStat(Stats.USE_CAULDRON)
            player.incrementStat(Stats.USED.getOrCreateStat(item))
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos)
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f)
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos)
        }
        return ActionResult.success(world.isClient())
    }
}