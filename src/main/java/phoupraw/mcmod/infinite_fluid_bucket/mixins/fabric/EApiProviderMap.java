package phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

public interface EApiProviderMap<K, V> {
    /**
     @return A read-only {@link Map} view of this instance. All modification operations will throw {@link UnsupportedOperationException}, except {@link Map#putIfAbsent(Object, Object)}.
     */
    @UnmodifiableView
    Map<K, V> asMap();
}
