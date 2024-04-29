package phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

public interface EItemApiLookup<A, C> {
    Event<ItemApiLookup.ItemApiProvider<A, C>> preliminary();
    
    @UnmodifiableView
    Map<Item, Event<ItemApiLookup.ItemApiProvider<A, C>>> itemSpecific();
    
    @NotNull
    Event<ItemApiLookup.ItemApiProvider<A, C>> getSpecificFor(@NotNull Item item);
    
    Event<ItemApiLookup.ItemApiProvider<A, C>> fallback();
}
