package phoupraw.mcmod.infinite_fluid_bucket.constant;

import lombok.SneakyThrows;
import org.jetbrains.annotations.ApiStatus;

import java.lang.invoke.MethodHandles;

@ApiStatus.Internal
public final class IFBRegistryInitializer implements IFBIDs, IFBFluidTags, IFBItems, IFBGameRules {
    @SneakyThrows
    public IFBRegistryInitializer() {
        for (Class<?> i : getClass().getInterfaces()) {
            MethodHandles.lookup().ensureInitialized(i);
        }
    }
}
