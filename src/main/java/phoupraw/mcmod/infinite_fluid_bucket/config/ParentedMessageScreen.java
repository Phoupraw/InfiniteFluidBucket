package phoupraw.mcmod.infinite_fluid_bucket.config;

import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ParentedMessageScreen extends MessageScreen {
    public final Screen parent;
    public ParentedMessageScreen(Text text, Screen parent) {
        super(text);
        this.parent = parent;
    }
    @Override
    public void close() {
        if (client != null) {
            client.setScreen(parent);
        }
    }
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
