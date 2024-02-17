package phoupraw.mcmod.infinite_fluid_bucket.config

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.SerialEntry
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen
import dev.isxander.yacl3.config.v2.api.autogen.TickBox
import dev.isxander.yacl3.config.v2.impl.serializer.GsonConfigSerializer
import phoupraw.mcmod.infinite_fluid_bucket.ID
import phoupraw.mcmod.linked.fabric.loader.Fabric

class InfiniteFluidBucketConfig {
    @AutoGen(category = CATEGORY)
    @SerialEntry
    @TickBox
    @JvmField
    var waterBucket = true
    @AutoGen(category = CATEGORY)
    @SerialEntry
    @TickBox
    @JvmField
    var emptyBucket = true
    @AutoGen(category = CATEGORY)
    @SerialEntry
    @TickBox
    @JvmField
    var milkBucket = true
    @AutoGen(category = CATEGORY)
    @SerialEntry
    @TickBox
    @JvmField
    var glassBottle = true
    @AutoGen(category = CATEGORY)
    @SerialEntry
    @TickBox
    @JvmField
    var waterPotion = true

    companion object {
        const val CATEGORY = "only"
        @JvmField
        val HANDLER: ConfigClassHandler<InfiniteFluidBucketConfig> = ConfigClassHandler.createBuilder(InfiniteFluidBucketConfig::class.java)
          .id(ID("only"))
          .serializer {
              GsonConfigSerializer.Builder(it)
                .setJson5(true)
                .setPath(Fabric.configDir.resolve("$ID.cfg.json5"))
                .build()
          }
          .build()
          .apply { load() }
    }
}