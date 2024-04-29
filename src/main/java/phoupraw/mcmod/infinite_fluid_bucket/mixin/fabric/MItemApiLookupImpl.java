package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.impl.lookup.item.ItemApiLookupImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Map;
import java.util.Objects;

@Mixin(value = ItemApiLookupImpl.class, remap = false)
abstract class MItemApiLookupImpl<A, C> implements ItemApiLookup<A, C> {
    private static <A, C> Event<ItemApiProvider<A, C>> newEvent() {
        return EventFactory.createArrayBacked(ItemApiProvider.class, providers -> (itemStack, context) -> {
            for (ItemApiProvider<A, C> provider : providers) {
                A api = provider.find(itemStack, context);
                if (api != null) return api;
            }
            
            return null;
        });
    }
    private final Event<ItemApiProvider<A, C>> preliminary = newEvent();
    private final ApiProviderMap<Item, Event<ItemApiProvider<A, C>>> itemSpecific = ApiProviderMap.create();
    private final Event<ItemApiProvider<A, C>> fallback = newEvent();
    @Override
    @Deprecated(forRemoval = true)
    public @Nullable ItemApiProvider<A, C> getProvider(@NotNull Item item) {
        var event = itemSpecific.get(item);
        return event == null ? null : (itemStack, context) -> event.invoker().find(itemStack, context);
    }
    @Override
    public Event<ItemApiProvider<A, C>> preliminary() {
        return preliminary;
    }
    
    @Override
    public Map<@NotNull Item, @NotNull Event<ItemApiProvider<A, C>>> itemSpecific() {
        return itemSpecific.asMap();
    }
    
    @Override
    public @NotNull Event<ItemApiProvider<A, C>> getSpecificFor(@NotNull Item item) {
        Event<ItemApiProvider<A, C>> event = itemSpecific.get(item);
        
        if (event == null) {
            itemSpecific.putIfAbsent(item, newEvent());
            event = Objects.requireNonNull(itemSpecific.get(item));
        }
        
        return event;
    }
    
    @Override
    public Event<ItemApiProvider<A, C>> fallback() {
        return fallback;
    }
    @Nullable
    @Override
    public A find(@NotNull ItemStack itemStack, C context) {
        A api = preliminary().invoker().find(itemStack, context);
        if (api != null) return api;
        
        if (itemSpecific().containsKey(itemStack.getItem())) {
            api = getSpecificFor(itemStack.getItem()).invoker().find(itemStack, context);
            if (api != null) return api;
        }
        
        return fallback().invoker().find(itemStack, context);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void registerSelf(ItemConvertible @NotNull ... items) {
        for (ItemConvertible itemConvertible : items) {
            Item item = itemConvertible.asItem();
            
            if (!apiClass().isAssignableFrom(item.getClass())) {
                String errorMessage = String.format(
                  "Failed to register self-implementing items. API class %s is not assignable from item class %s.",
                  apiClass().getCanonicalName(),
                  item.getClass().getCanonicalName()
                );
                throw new IllegalArgumentException(errorMessage);
            }
        }
        
        registerForItems((itemStack, context) -> (A) itemStack.getItem(), items);
    }
    @Override
    public void registerForItems(@NotNull ItemApiProvider<A, C> provider, ItemConvertible @NotNull ... items) {
        Objects.requireNonNull(provider, "ItemApiProvider may not be null.");
        
        if (items.length == 0) {
            throw new IllegalArgumentException("Must register at least one ItemConvertible instance with an ItemApiProvider.");
        }
        
        for (ItemConvertible itemConvertible : items) {
            Item item = itemConvertible.asItem();
            Objects.requireNonNull(item, "Item convertible in item form may not be null.");
            getSpecificFor(item).register(provider);
        }
    }
    @Override
    public void registerFallback(@NotNull ItemApiProvider<A, C> fallbackProvider) {
        fallback().register(fallbackProvider);
    }
}
