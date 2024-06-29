package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(EditGameRulesScreen.class)
public interface AEditGameRulesScreen {
    @Accessor
    ThreePartsLayoutWidget getLayout();
}
