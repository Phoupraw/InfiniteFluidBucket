package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.util.Identifier;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

public sealed interface IFBIDs permits InterfaceFinalizer {
    static Identifier of(String path) {
        return new Identifier(InfiniteFluidBucket.ID, path);
    }
}