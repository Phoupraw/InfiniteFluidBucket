package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientConfigurationNetworkHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin({ClientConfigurationNetworkHandler.class, ClientPlayNetworkHandler.class})
abstract class MClientCommonPacketListener4TagSync {
    //@Inject(method = "onSynchronizeTags", at = @At("RETURN"))
    //private void tagSynced(SynchronizeTagsS2CPacket packet, CallbackInfo ci) {
    //    InfiniteFluidBucketClient.TAGS_SYNCHRONIZED.invoker().run();
    //}
}
