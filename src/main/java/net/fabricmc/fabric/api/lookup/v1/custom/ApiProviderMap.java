package net.fabricmc.fabric.api.lookup.v1.custom;

import net.fabricmc.fabric.impl.lookup.custom.ApiProviderHashMap;
import org.jetbrains.annotations.Nullable;
import phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric.EApiProviderMap;

public interface ApiProviderMap<K, V> extends EApiProviderMap<K, V> {
    static <K, V> net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap<K, V> create() {
        return new ApiProviderHashMap<>();
    }
    @Nullable
    V get(K key);
    @Nullable
    V putIfAbsent(K key, V provider);
}
