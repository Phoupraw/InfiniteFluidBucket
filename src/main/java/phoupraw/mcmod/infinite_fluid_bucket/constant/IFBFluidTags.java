package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public sealed interface IFBFluidTags permits InterfaceFinalizer {
    //TagKey<Fluid>
    //  EMPTY_BUCKET = of("empty_bucket"),
    //  EMPTY_BOTTLE = of("empty_bottle");
    private static TagKey<Fluid> of(String path) {
        return TagKey.of(RegistryKeys.FLUID, IFBIDs.of(path));
    }
}
