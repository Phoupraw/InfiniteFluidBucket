package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.lookup.v1.custom.ApiProviderMap;
import org.spongepowered.asm.mixin.Mixin;
import phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric.EApiProviderMap;

@Mixin(value = ApiProviderMap.class, remap = false)
interface MApiProviderMap<K, V> extends EApiProviderMap<K, V> {
}
