package phoupraw.mcmod.infinite_fluid_bucket.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

@Mixin(CowEntity.class)
abstract class MCowEntity extends AnimalEntity {
    protected MCowEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @ModifyExpressionValue(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean checkInf(boolean original, PlayerEntity player, Hand hand) {
        return original && !(IFBConfig.getConfig().isEmptyBucket() && Infinities.hasInfinity(player.getStackInHand(hand), getRegistryManager()));
    }
}
