package phoupraw.mcmod.infinite_fluid_bucket.misc;

import lombok.experimental.UtilityClass;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

@UtilityClass
public class Misc {
    public static final ItemStack WATER_POTION = PotionUtil.setPotion(Items.POTION.getDefaultStack(), Potions.WATER);
}
