package phoupraw.mcmod.infinite_fluid_bucket.mixin.fabric;

import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import org.spongepowered.asm.mixin.Mixin;
import phoupraw.mcmod.infinite_fluid_bucket.mixins.fabric.EItemApiLookup;

@Mixin(ItemApiLookup.class)
interface MItemApiLookup<A, C> extends EItemApiLookup<A, C> {

}
