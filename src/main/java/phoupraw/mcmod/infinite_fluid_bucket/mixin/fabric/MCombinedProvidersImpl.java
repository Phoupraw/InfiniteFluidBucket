package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.impl.transfer.fluid.CombinedProvidersImpl;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric.EItemApiLookup;

import java.util.HashMap;
import java.util.Map;

@Mixin(CombinedProvidersImpl.class)
abstract class MCombinedProvidersImpl {
    private static final Map<Item, CombinedProvidersImpl.Provider> ITEM_SPECIFIC = new HashMap<>();
    /**
     @author Phoupraw
     @reason for {@link EItemApiLookup}
     */
    @Overwrite
    public static Event<FluidStorage.CombinedItemApiProvider> getOrCreateItemEvent(Item item) {
        CombinedProvidersImpl.Provider provider = ITEM_SPECIFIC.get(item);
        
        if (provider == null) {
            synchronized (ITEM_SPECIFIC) {
                provider = ITEM_SPECIFIC.get(item);
                
                if (provider == null) {
                    provider = new CombinedProvidersImpl.Provider();
                    ITEM_SPECIFIC.putIfAbsent(item, provider);
                    FluidStorage.ITEM.getSpecificFor(item).register(provider);
                }
            }
        }
        
        return provider.event;
    }
}
