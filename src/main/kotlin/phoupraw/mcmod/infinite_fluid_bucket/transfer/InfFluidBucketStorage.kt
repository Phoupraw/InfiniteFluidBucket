@file:Suppress("INAPPLICABLE_JVM_NAME")

package phoupraw.mcmod.infinite_fluid_bucket.transfer

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.storage.base.ExtractionOnlyStorage
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext
import kotlin.math.min

class InfFluidBucketStorage(override val resource: FluidVariant, override val capacity: Long) : SingleSlotStorage<FluidVariant>, ExtractionOnlyStorage<FluidVariant> {
    override fun extract(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long = if (this.resource == resource) min(maxAmount, amount) else 0
    override val resourceBlank: Boolean @JvmName("isResourceBlank") get() = false
    override val amount: Long get() = capacity
}
