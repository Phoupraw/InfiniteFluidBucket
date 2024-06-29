package phoupraw.mcmod.infinite_fluid_bucket.config;

import net.minecraft.world.GameRules;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;
import phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft.AGameRules;

public class GameRulesInstance extends GameRules {
    @SuppressWarnings({"unchecked"})
    public void iterate(GameRules.Visitor visitor) {
        for (Key<BooleanRule> key : IFBGameRules.KEYS.values()) {
            visitor.visitBoolean(key, (Type<BooleanRule>) AGameRules.getRULE_TYPES().get(key));
        }
    }
}
