@file:Suppress("INAPPLICABLE_JVM_NAME")

package phoupraw.mcmod.infinite_fluid_bucket.transfer

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.storage.base.InsertionOnlyStorage
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext

open class InfEmptyFluidStorage(override val capacity: Long) : SingleSlotStorage<FluidVariant>, InsertionOnlyStorage<FluidVariant> {
    override fun extract(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long = super.extract(resource, maxAmount, transaction)
    override fun insert(resource: FluidVariant, maxAmount: Long, transaction: TransactionContext): Long = if (maxAmount >= capacity) capacity else 0
    override val resourceBlank: Boolean @JvmName("isResourceBlank") get() = true
    override val resource: FluidVariant get() = FluidVariant.blank()
    override val amount: Long get() = 0
    override fun iterator(): Iterator<SingleSlotStorage<FluidVariant>> = super<SingleSlotStorage>.iterator()
}
