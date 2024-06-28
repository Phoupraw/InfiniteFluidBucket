package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class IFBDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator g) {
        var pack = g.createPack();
        pack.addProvider(ItemTag::new);
        pack.addProvider(FluidTag::new);
        pack.addProvider(EnchTag::new);
        //client
        pack.addProvider(Chinese::new);
        pack.addProvider(English::new);
    }
}
