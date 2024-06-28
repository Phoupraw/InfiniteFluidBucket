package phoupraw.mcmod.infinite_fluid_bucket.constant;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.ApiStatus;

import java.lang.invoke.MethodHandles;

@ApiStatus.Internal
@UtilityClass
public final class IFBConstants implements IFBIDs, IFBFluidTags, IFBItems, IFBGameRules, IFBItemTags, IFBEnchantmentTags {
    @SneakyThrows
    public static void loadClasses() {
        for (Class<?> i : IFBConstants.class.getInterfaces()) {
            MethodHandles.lookup().ensureInitialized(i);
        }
    }
}
