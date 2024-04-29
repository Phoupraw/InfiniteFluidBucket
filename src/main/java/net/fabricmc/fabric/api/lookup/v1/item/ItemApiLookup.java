package net.fabricmc.fabric.api.lookup.v1.item;

import net.fabricmc.fabric.impl.lookup.item.ItemApiLookupImpl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric.EItemApiLookup;

public interface ItemApiLookup<A, C> extends EItemApiLookup<A, C> {
    static <A, C> ItemApiLookup<A, C> get(Identifier lookupId, Class<A> apiClass, Class<C> contextClass) {
        return ItemApiLookupImpl.get(lookupId, apiClass, contextClass);
    }
    @Nullable
    A find(ItemStack itemStack, C context);
    void registerSelf(ItemConvertible... items);
    void registerForItems(ItemApiProvider<A, C> provider, ItemConvertible... items);
    void registerFallback(ItemApiProvider<A, C> fallbackProvider);
    Identifier getId();
    Class<A> apiClass();
    Class<C> contextClass();
    @Nullable
    ItemApiProvider<A, C> getProvider(Item item);
    @FunctionalInterface
    interface ItemApiProvider<A, C> {
        @Nullable
        A find(ItemStack itemStack, C context);
    }
}
