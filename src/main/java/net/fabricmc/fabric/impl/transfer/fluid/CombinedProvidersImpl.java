package net.fabricmc.fabric.impl.transfer.fluid;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CombinedProvidersImpl {
    public static Event<FluidStorage.CombinedItemApiProvider> createEvent(boolean invokeFallback) {
        return EventFactory.createArrayBacked(FluidStorage.CombinedItemApiProvider.class, listeners -> context -> {
            List<Storage<FluidVariant>> storages = new ArrayList<>();
            
            for (FluidStorage.CombinedItemApiProvider listener : listeners) {
                Storage<FluidVariant> found = listener.find(context);
                
                if (found != null) {
                    storages.add(found);
                }
            }
            
            // Allow combining per-item combined providers with fallback combined providers.
            if (!storages.isEmpty() && invokeFallback) {
                // Only invoke the fallback if API Lookup doesn't invoke it right after,
                // that is only invoke the fallback if storages were offered,
                // otherwise we can wait for API Lookup to invoke the fallback provider itself.
                Storage<FluidVariant> fallbackFound = FluidStorage.GENERAL_COMBINED_PROVIDER.invoker().find(context);
                
                if (fallbackFound != null) {
                    storages.add(fallbackFound);
                }
            }
            
            return storages.isEmpty() ? null : new CombinedStorage<>(storages);
        });
    }
    public static Event<FluidStorage.CombinedItemApiProvider> getOrCreateItemEvent(Item item) {
        ItemApiLookup.ItemApiProvider<Storage<FluidVariant>, ContainerItemContext> existingProvider = FluidStorage.ITEM.getProvider(item);
        
        if (existingProvider == null) {
            FluidStorage.ITEM.registerForItems(new Provider(), item);
            // The provider might not be new Provider() if a concurrent registration happened, re-query.
            existingProvider = FluidStorage.ITEM.getProvider(item);
        }
        
        if (existingProvider instanceof Provider registeredProvider) {
            return registeredProvider.event;
        } else {
            String errorMessage = String.format(
              "An incompatible provider was already registered for item %s. Provider: %s.",
              item,
              existingProvider
            );
            throw new IllegalStateException(errorMessage);
        }
    }
    
    @ApiStatus.Internal
    public static class Provider implements ItemApiLookup.ItemApiProvider<Storage<FluidVariant>, ContainerItemContext> {
        @ApiStatus.Internal
        public final Event<FluidStorage.CombinedItemApiProvider> event = createEvent(true);
        
        @Override
        @Nullable
        public Storage<FluidVariant> find(ItemStack itemStack, ContainerItemContext context) {
            if (!context.getItemVariant().matches(itemStack)) {
                String errorMessage = String.format(
                  "Query stack %s and ContainerItemContext variant %s don't match.",
                  itemStack,
                  context.getItemVariant()
                );
                throw new IllegalArgumentException(errorMessage);
            }
            
            return event.invoker().find(context);
        }
    }
}
