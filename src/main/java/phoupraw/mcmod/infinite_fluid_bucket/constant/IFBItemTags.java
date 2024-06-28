package phoupraw.mcmod.infinite_fluid_bucket.constant;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public sealed interface IFBItemTags permits IFBConstants {
    @Deprecated
    TagKey<Item> INFINITABLE = of("infinitable");
    TagKey<Item> INSERTABLE = of("insertable");
    TagKey<Item> EXTRACTABLE = of("extractable");
    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, IFBIDs.of(path));
    }
    
}
