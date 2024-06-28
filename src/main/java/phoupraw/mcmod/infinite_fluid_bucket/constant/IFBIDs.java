package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.util.Identifier;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

public sealed interface IFBIDs permits IFBConstants {
    Identifier PHASE = of("common");
    static Identifier of(String path) {
        return Identifier.of(InfiniteFluidBucket.ID, path);
    }
}
