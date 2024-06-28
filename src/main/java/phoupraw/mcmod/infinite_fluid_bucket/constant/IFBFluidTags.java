package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public sealed interface IFBFluidTags permits IFBConstants {
    @Deprecated
    TagKey<Fluid> INFINITABLE = of("infinitable");
    TagKey<Fluid> BUCKET = of("bucket");
    TagKey<Fluid> GLASS_BOTTLE = of("glass_bottle");
    TagKey<Fluid> INSERTABLE = of("insertable");
    TagKey<Fluid> EXTRACTABLE = of("extractable");
    private static TagKey<Fluid> of(String path) {
        return TagKey.of(RegistryKeys.FLUID, IFBIDs.of(path));
    }
}
