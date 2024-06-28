package phoupraw.mcmod.infinite_fluid_bucket;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import phoupraw.mcmod.infinite_fluid_bucket.config.IFBConfig;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;
import phoupraw.mcmod.infinite_fluid_bucket.misc.Infinities;

public final class InfiniteFluidBucketClient implements ClientModInitializer {
    public static final Event<Runnable> TAGS_SYNCHRONIZED = EventFactory.createArrayBacked(Runnable.class, callbacks -> () -> {
        for (Runnable callback : callbacks) {
            callback.run();
        }
    });
    //public static final Object2BooleanMap<String> GAME_RULES = new Object2BooleanArrayMap<>();
    @Override
    public void onInitializeClient() {
        //ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> InfiniteFluidBucket.reflectTagChanges());
        TAGS_SYNCHRONIZED.register(InfiniteFluidBucket::reflectTagChanges);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            RegistryEntry<Enchantment> infinity = Infinities.getInfinity(entries.getContext().lookup());
            if (IFBConfig.getConfig().isEmptyBucket()) {
                entries.addAfter(Items.WATER_BUCKET, Infinities.addInfinity(Items.WATER_BUCKET.getDefaultStack(), infinity));
            }
            if (IFBConfig.getConfig().isWaterBucket()) {
                entries.addAfter(Items.BUCKET, Infinities.addInfinity(Items.BUCKET.getDefaultStack(), infinity));
            }
            if (IFBConfig.getConfig().isMilkBucket()) {
                entries.addAfter(Items.MILK_BUCKET, Infinities.addInfinity(Items.MILK_BUCKET.getDefaultStack(), infinity));
            }
            if (IFBConfig.isLavaBucket()) {
                entries.addAfter(Items.LAVA_BUCKET, Infinities.addInfinity(Items.LAVA_BUCKET.getDefaultStack(), infinity));
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            RegistryWrapper.WrapperLookup lookup = entries.getContext().lookup();
            if (IFBConfig.getConfig().isWaterPotion()) {
                ItemStack water = PotionContentsComponent.createStack(Items.POTION, Potions.WATER);
                entries.addAfter(water, Infinities.addInfinity(water.copy(), lookup));
            }
            if (IFBConfig.getConfig().isMilkBucket()) {
                entries.addAfter(Items.MILK_BUCKET, Infinities.addInfinity(Items.MILK_BUCKET.getDefaultStack(), lookup));
            }
            if (IFBConfig.isHoneyBottle()) {
                entries.addAfter(Items.HONEY_BOTTLE, Infinities.addInfinity(Items.HONEY_BOTTLE.getDefaultStack(), lookup));
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            if (IFBConfig.getConfig().isGlassBottle()) {
                entries.addAfter(Items.GLASS_BOTTLE, Infinities.addInfinity(Items.GLASS_BOTTLE.getDefaultStack(), entries.getContext().lookup()));
            }
        });
        ClientPlayNetworking.registerGlobalReceiver(IFBGameRules.BoolGameRulePayload.ID, (payload, context) -> {
            IFBGameRules.VALUES.put(payload.name(), payload.value());
            //GameRules.accept(new GameRules.Visitor() {
            //    @Override
            //    public <T extends GameRules.Rule<T>> void visit(GameRules.Key<T> key, GameRules.Type<T> type) {
            //        if (payload.name().equals(key.getName())) {
            //            IFBGameRules.GAME_RULES.get(key).setValue();
            //        }
            //    }
            //});
        });
        //ItemGroupEvents.MODIFY_ENTRIES_ALL
    }
}
