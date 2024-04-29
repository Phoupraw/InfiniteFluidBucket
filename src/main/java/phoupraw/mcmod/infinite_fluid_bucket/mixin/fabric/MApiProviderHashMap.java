package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap;
import net.fabricmc.fabric.impl.lookup.custom.ApiProviderHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Mixin(value = ApiProviderHashMap.class, remap = false)
abstract class MApiProviderHashMap<K, V> implements ApiProviderMap<K, V>, @UnmodifiableView Map<K, V> {
    @Shadow
    private volatile Map<K, V> lookups;
    @Shadow
    @Override
    public abstract @Nullable V get(Object key);
    @Shadow
    @Override
    public abstract @Nullable V putIfAbsent(K key, V provider);
    @Override
    public int size() {
        return lookups.size();
    }
    
    @Override
    public boolean isEmpty() {
        return lookups.isEmpty();
    }
    
    @Override
    public boolean containsKey(Object key) {
        return lookups.containsKey(key);
    }
    
    @Override
    public boolean containsValue(Object value) {
        return lookups.containsValue(value);
    }
    
    @Override
    public @Nullable V put(K key, V value) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public @NotNull Set<K> keySet() {
        return Collections.unmodifiableSet(lookups.keySet());
    }
    
    @Override
    public @NotNull Collection<V> values() {
        return Collections.unmodifiableCollection(lookups.values());
    }
    
    @Override
    public @NotNull Set<Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(lookups.entrySet());
    }
    
    @Override
    public @UnmodifiableView Map<K, V> asMap() {
        return this;
    }
}
