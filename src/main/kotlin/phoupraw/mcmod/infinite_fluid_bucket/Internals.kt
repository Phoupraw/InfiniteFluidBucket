@file:JvmName("Internals")
package phoupraw.mcmod.infinite_fluid_bucket

import com.google.common.collect.Interner
import com.google.common.collect.Interners
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
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
internal fun ID(path: String): Identifier = IDS.intern(Identifier(ID, path))
internal var ItemStack.infinity
    @JvmName("isInfinity")
    get() = EnchantmentHelper.getLevel(Enchantments.INFINITY, this) > 0
    set(value) = EnchantmentHelper.set(EnchantmentHelper.get(this).also {
        if (value) it[Enchantments.INFINITY] = max(it.getOrDefault(Enchantments.INFINITY, 0), 1)
        else it -= Enchantments.INFINITY
    }, this)
val Storage<*>.blank @JvmName("isBlank") get() = !supportsInsertion() && !supportsExtraction() && none()