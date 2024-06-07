package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$PotionSlot")
abstract class MBrewingStandScreenHandler_PotionSlot extends Slot {
    public MBrewingStandScreenHandler_PotionSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @ModifyReturnValue(method = "matches", at = @At("RETURN"))
    private static boolean checkInf(boolean original, ItemStack stack) {
        return original && !Infinities.isInfinityPotion(stack);
    }
}
