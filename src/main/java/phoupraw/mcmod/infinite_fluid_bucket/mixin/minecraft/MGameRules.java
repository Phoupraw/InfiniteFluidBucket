package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GameRules.class)
class MGameRules {
    
    //@Inject(method = "register", at = @At("RETURN"))
    //private static <T extends GameRules.Rule<T>> void setKey(String name, GameRules.Category category, GameRules.Type<T> type, CallbackInfoReturnable<GameRules.Key<T>> cir) {
    //    type.setKey(cir.getReturnValue());
    //}
    
}
