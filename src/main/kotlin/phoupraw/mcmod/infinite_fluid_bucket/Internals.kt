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

internal const val ID = InfiniteFluidBucket.ID
internal fun ID(path: String): Identifier = IDS.intern(Identifier(ID, path))
internal val LOGGER: Logger = LogManager.getLogger(ID)
//internal val CONFIG get() = TrifleStorageConfig.HANDLER
private val IDS: Interner<Identifier> = Interners.newBuilder().build()
internal var ItemStack.infinity
    get() = EnchantmentHelper.getLevel(Enchantments.INFINITY, this)
    set(value) = EnchantmentHelper.set(EnchantmentHelper.get(this).also {
        if (value == 0) it -= Enchantments.INFINITY
        else it[Enchantments.INFINITY] = value
    }, this)
val Storage<*>.blank @JvmName("isBlank") get() = !supportsInsertion() && !supportsExtraction() && none()