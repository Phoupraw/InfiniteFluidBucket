package phoupraw.mcmod.infinite_fluid_bucket.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import phoupraw.mcmod.infinite_fluid_bucket.CONFIG

@Suppress("unused")
object InfiniteFluidBucketModMenu : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory {
            CONFIG.load()
            CONFIG.generateGui().generateScreen(it)
        }
    }
}