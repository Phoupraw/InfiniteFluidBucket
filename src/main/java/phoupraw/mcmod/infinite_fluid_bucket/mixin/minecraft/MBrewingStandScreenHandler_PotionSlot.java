package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(targets = "net.minecraft.screen.BrewingStandScreenHandler$PotionSlot")
abstract class MBrewingStandScreenHandler_PotionSlot extends Slot {
    public MBrewingStandScreenHandler_PotionSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    private static void checkInf(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (Infinities.isInfinityPotion(stack)) {
            cir.setReturnValue(false);
        }
    }
}
