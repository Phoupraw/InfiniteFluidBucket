@file:Suppress("UNUSED_ANONYMOUS_PARAMETER", "UNUSED_PARAMETER")

package phoupraw.mcmod.infinite_fluid_bucket

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.server.MinecraftServer
import phoupraw.mcmod.infinite_fluid_bucket.transfer.SimulationStorage
import phoupraw.mcmod.linked.fabric.transfer.fluid.FluidStorages
import phoupraw.mcmod.linked.fabric.transfer.storage.ContainerItemContext
import phoupraw.mcmod.linked.lang.GenericApiLookup
import phoupraw.mcmod.linked.lang.MutableEvent

object InfiniteFluidBucket : ModInitializer {
    const val ID = "infinite_fluid_bucket"
    @JvmField
    val INFINITY = GenericApiLookup<Item, Storage<FluidVariant>, ContainerItemContext>("$ID:infinity")
    @JvmField
    val INFINITE: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, ID("infinite"))
    @JvmField
    val INF_FLUIDS = mutableSetOf<FluidVariant>()
    private val items = mutableSetOf<Item>()
    @JvmStatic
    fun isInfinity(itemStack: ItemStack): Boolean = FluidStorages.ITEM(itemStack.item, ContainerItemContext(itemStack)) is SimulationStorage
    override fun onInitialize() {
        FluidStorages.ITEM.preliminary.register { key, context -> INFINITY(key, context)?.takeUnless { it.blank } }
        ServerLifecycleEvents.SERVER_STARTED.register(::onDatapackReload)
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register { server, serverResourceManager, success -> onDatapackReload(server) }
    }

    private fun onDatapackReload(server: MinecraftServer) {
        for (entry in Registries.ITEM.iterateEntries(INFINITE)) {
            val emptyItem = entry.value()
            if (!items.add(emptyItem)) continue
            FluidStorages.ITEM[emptyItem].addKeyOrder(ID, MutableEvent.DEFAULT_KEY)
            FluidStorages.ITEM[emptyItem].register(ID, ::provideInfStorage)
        }
    }
    @JvmStatic
    private fun provideInfStorage(item: Item, context: ContainerItemContext): Storage<FluidVariant>? {
        val stack = context.itemVariant.toStack()
        if (stack.isIn(INFINITE) && stack.infinity >= 1) {
            stack.infinity = 0
            FluidStorages.ITEM(item, ContainerItemContext(stack))?.let(::SimulationStorage)
        }
        return null
    }
}