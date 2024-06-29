package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.config.GameRulesInstance;

@Environment(EnvType.CLIENT)
@Mixin(EditGameRulesScreen.RuleListWidget.class)
abstract class MEditGameRulesScreen_RuleListWidget {
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;accept(Lnet/minecraft/world/GameRules$Visitor;)V"))
    private void customGameRulesInstance(GameRules.Visitor visitor, Operation<Void> original, @Local(argsOnly = true) GameRules gameRules) {
        if (gameRules instanceof GameRulesInstance instance) {
            instance.iterate(visitor);
        } else {
            original.call(visitor);
        }
    }
}
