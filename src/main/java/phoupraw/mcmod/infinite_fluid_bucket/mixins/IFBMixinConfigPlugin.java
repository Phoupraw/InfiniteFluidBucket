package phoupraw.mcmod.infinite_fluid_bucket.mixins;

import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class IFBMixinConfigPlugin implements IMixinConfigPlugin {
    public static final Set<String> DISABLED_CLASSES = new HashSet<>();
    public static final TypeToken<Collection<String>> JSON_TYPE = new TypeToken<>() {};
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(InfiniteFluidBucket.ID + ".mixin.json");
    //@SneakyThrows
    @Override
    public void onLoad(String mixinPackage) {
        //Gson gson = new Gson();
        //if (Files.exists(CONFIG_PATH)) {
        //    try (var reader = new JsonReader(Files.newBufferedReader(CONFIG_PATH))) {
        //        DISABLED_CLASSES.addAll(gson.fromJson(reader, JSON_TYPE));
        //    }
        //}
        //try (var writer = new JsonWriter(Files.newBufferedWriter(CONFIG_PATH))) {
        //    gson.toJson(DISABLED_CLASSES, Collection.class, writer);
        //}
    }
    @Override
    public @Nullable String getRefMapperConfig() {
        return null;
    }
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }
    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    
    }
    @Override
    public @Nullable List<String> getMixins() {
        return null;
    }
    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //InfiniteFluidBucket.LOGGER.info("preApply {}", mixinInfo.getName());
    }
    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //InfiniteFluidBucket.LOGGER.info("postApply {}", mixinInfo.getName());
    }
}
