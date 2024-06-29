package phoupraw.mcmod.infinite_fluid_bucket.config;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DialogScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.util.List;

public class TipDialogScreen extends DialogScreen {
    public static final String NO_WORLD = "config." + InfiniteFluidBucket.ID + ".no_world";
    public static final String NO_PERMISSION = "config." + InfiniteFluidBucket.ID + ".no_permission";
    private final Screen parent;
    public TipDialogScreen(Screen parent, Screen next, String message) {
        super(
          Text.translatable("createWorld.customize.custom.confirmTitle"),
          List.of(Text.translatable(message)),
          ImmutableList.of(new ChoiceButton(ScreenTexts.CONTINUE, button -> MinecraftClient.getInstance().setScreen(next))));
        this.parent = parent;
    }
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
    @Override
    public void close() {
        if (client != null) {
            client.setScreen(parent);
        }
    }
}
