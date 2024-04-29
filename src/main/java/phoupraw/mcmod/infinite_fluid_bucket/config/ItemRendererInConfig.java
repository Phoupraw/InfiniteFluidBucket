package phoupraw.mcmod.infinite_fluid_bucket.config;

import dev.isxander.yacl3.gui.image.ImageRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public record ItemRendererInConfig(ItemStack itemStack) implements ImageRenderer {
    public static CompletableFuture<Optional<ImageRenderer>> of(ItemStack itemStack) {
        return CompletableFuture.completedFuture(Optional.of(new ItemRendererInConfig(itemStack)));
    }
    @Override
    public int render(DrawContext graphics, int x, int y, int renderWidth, float tickDelta) {
        graphics.drawItem(itemStack(), x, y);
        return 16;
    }
    @Override
    public void close() {
    
    }
}
