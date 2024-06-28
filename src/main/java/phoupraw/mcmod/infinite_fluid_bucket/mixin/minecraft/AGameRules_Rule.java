package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameRules.Rule.class)
public interface AGameRules_Rule {
    //@Accessor
    //default <T extends GameRules.Rule<T>> GameRules.Type<T> getType() {
    //    throw new AssertionError();
    //}
}
