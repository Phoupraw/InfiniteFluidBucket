@file:JvmName("Internals")

package phoupraw.mcmod.infinite_fluid_bucket

import com.google.common.collect.Interner
import com.google.common.collect.Interners
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.minecraft.block.dispenser.DispenserBehavior
import net.minecraft.block.dispenser.ItemDispenserBehavior
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.potion.PotionUtil
import net.minecraft.potion.Potions
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import phoupraw.mcmod.infinite_fluid_bucket.config.InfiniteFluidBucketConfig
import kotlin.math.max

internal const val ID = InfiniteFluidBucket.ID
@JvmField
internal val LOGGER: Logger = LogManager.getLogger(ID)
private val IDS: Interner<Identifier> = Interners.newBuilder().build()
@JvmField
internal val CONFIG = InfiniteFluidBucketConfig.HANDLER
@JvmField
internal val DISPENSER_FALLBACK: DispenserBehavior = ItemDispenserBehavior()
internal fun ID(path: String): Identifier = IDS.intern(Identifier(ID, path))
internal var ItemStack.infinity
    @JvmName("isInfinity")
    get() = EnchantmentHelper.getLevel(Enchantments.INFINITY, this) > 0
    set(value) = EnchantmentHelper.set(EnchantmentHelper.get(this).also {
        if (value) it[Enchantments.INFINITY] = max(it.getOrDefault(Enchantments.INFINITY, 0), 1)
        else it -= Enchantments.INFINITY
    }, this)
internal fun ItemStack.isWaterPotion() = isOf(Items.POTION) && PotionUtil.getPotion(this) == Potions.WATER
val Storage<*>.blank @JvmName("isBlank") get() = !supportsInsertion() && !supportsExtraction() && none()