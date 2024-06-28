package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientConfigurationNetworkHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.common.SynchronizeTagsS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucketClient;

@Environment(EnvType.CLIENT)
@Mixin({ClientConfigurationNetworkHandler.class, ClientPlayNetworkHandler.class})
abstract class MClientCommonPacketListener4TagSync {
    @Inject(method = "onSynchronizeTags", at = @At("RETURN"))
    private void tagSynced(SynchronizeTagsS2CPacket packet, CallbackInfo ci) {
        InfiniteFluidBucketClient.TAGS_SYNCHRONIZED.invoker().run();
    }
}
