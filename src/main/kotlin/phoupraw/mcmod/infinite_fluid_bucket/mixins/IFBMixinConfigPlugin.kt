package phoupraw.mcmod.infinite_fluid_bucket.mixins

import org.objectweb.asm.tree.ClassNode
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
import org.spongepowered.asm.mixin.extensibility.IMixinInfo

class IFBMixinConfigPlugin : IMixinConfigPlugin {
    override fun onLoad(mixinPackage: String) {
    }

    override fun getRefMapperConfig(): String? {
        return null
    }

    override fun shouldApplyMixin(targetClassName: String, mixinClassName: String): Boolean {
        return true/*mixinClassName !=
          if (FabricLoader.getInstance().isModLoaded("vmp")) "phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft.MIngredient"
          else "phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft.MIngredient4VMP"*/
    }

    override fun acceptTargets(myTargets: MutableSet<String>, otherTargets: Set<String>) {
    }

    override fun getMixins(): List<String>? {
        return null
    }

    override fun preApply(targetClassName: String?, targetClass: ClassNode?, mixinClassName: String?, mixinInfo: IMixinInfo?) {
    }

    override fun postApply(targetClassName: String?, targetClass: ClassNode?, mixinClassName: String?, mixinInfo: IMixinInfo?) {
    }
}