package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public sealed interface IFBEnchantmentTags permits IFBConstants {
    TagKey<Enchantment> INFINITIER = TagKey.of(RegistryKeys.ENCHANTMENT, IFBIDs.of("infinitier"));
}
