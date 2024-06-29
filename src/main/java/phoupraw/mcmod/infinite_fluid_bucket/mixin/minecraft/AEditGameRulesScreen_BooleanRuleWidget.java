package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EditGameRulesScreen.BooleanRuleWidget.class)
public interface AEditGameRulesScreen_BooleanRuleWidget {
    @Accessor
    CyclingButtonWidget<Boolean> getToggleButton();
}
