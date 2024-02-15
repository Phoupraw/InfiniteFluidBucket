package phoupraw.mcmod.infinite_fluid_bucket.transfer

import net.fabricmc.fabric.api.transfer.v1.storage.StorageView
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext
import phoupraw.mcmod.linked.fabric.transfer.storage.simulateExtract

//TODO linked
class SimulationStorageView<T>(val back: StorageView<T>) : StorageView<T> by back {
    override val underlyingView: StorageView<T> get() = back.underlyingView
    override fun extract(resource: T, maxAmount: Long, transaction: TransactionContext): Long = back.simulateExtract(transaction, resource, maxAmount)
}