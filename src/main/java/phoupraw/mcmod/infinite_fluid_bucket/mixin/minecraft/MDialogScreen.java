package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DialogScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import phoupraw.mcmod.infinite_fluid_bucket.config.TipDialogScreen;

@Environment(EnvType.CLIENT)
@Mixin(DialogScreen.class)
abstract class MDialogScreen extends Screen {
    protected MDialogScreen(Text title) {
        super(title);
    }
    @SuppressWarnings("UnreachableCode")
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/MultilineText;create(Lnet/minecraft/client/font/TextRenderer;I[Lnet/minecraft/text/Text;)Lnet/minecraft/client/font/MultilineText;"))
    private int expandWidth(int original) {
        return ((Object) this) instanceof TipDialogScreen ? width : original;
    }
}
