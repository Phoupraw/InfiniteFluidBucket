package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBFluidTags;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBGameRules;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBItemTags;

import java.util.concurrent.CompletableFuture;

import static phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.ID;

final class English extends FabricLanguageProvider {
    English(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder b) {
        String modName = "Infinite Fluid Bucket";
        String modded = modName + ": ";
        b.add(InfiniteFluidBucket.NAME_KEY, modName);
        b.add("modmenu.descriptionTranslation." + ID, """
          Enchant bucket with infinity!
          §lSynopsis§r
          - Bucket can be enchanted with infinity, allowing infinitly discarding fluid.
          - Water bucket can be enchanted with infinity, allowing infinite water.
          - Milk bucket can be enchanted with infinity, allowing infintely drinking.
          - Glass bottle can be enchanted with infinity, allowing infinitly discarding fluid.
          - Water potion can be enchanted with infinity, providing infinite water.
          - Optional infinity lava bucket and infinity honey bottle.
          - Customize any infinity fluid item.
          §lProfile§r
          Enchant buckets by enchanting table or anvil.
          Each item can be toggled independently by config file or Mod Menu.
          Can be installed only at server side, just client might have a few sync bugs.
          §lTrivia§r
          This mod is inspired by infinite water bucket of Quark mod.
          """);
        b.add("gamerule." + IFBGameRules.WATER_BUCKET, modded + "Infinity Water Bucket");
        b.add("gamerule." + IFBGameRules.WATER_BUCKET + ".description", """
          When enabled, water bucket can be enchanted with infinity.
          §lInfinity Water Bucket§r
          - Infinitely place water.
          - Infinitely fill cauldron.
          - Infinitely place water in dispenser.
          - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
          - Doesn't consume in crafting.
          - Can't fish.
          """);
        b.add("gamerule." + IFBGameRules.EMPTY_BUCKET, modded + "Infinity Empty Bucket");
        b.add("gamerule." + IFBGameRules.EMPTY_BUCKET + ".description", """
          When enabled, bucket can be enchanted with infinity.
          §lInfinity Empty Bucket§r
          - Infinitely scoop and void fluid.
          - Infinitely empty fluid in cauldron, including powder snow.
          - Infinitely scoop and void fluid in dispenser.
          - Can be inserted with fluid (must have bucket type) by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
          - Can't be used in crafting.
          - Can't milk.
          """);
        b.add("gamerule." + IFBGameRules.WATER_POTION, modded + "Infinity Water Potion");
        b.add("gamerule." + IFBGameRules.WATER_POTION + ".description", """
          When enabled, water potion can be enchanted with infinity.
          §lInfinity Water Potion§r
          - Infinitely fill cauldron.
          - Infinitely convert dirt into mud.
          - Infinitely provide water to fluid tank of mod. Each time one bottle of water.
          - Doesn't consume in crafting.
          - Can't be used in brewing.
          """);
        b.add("gamerule." + IFBGameRules.GLASS_BOTTLE, modded + "Infinity Empty Bottle");
        b.add("gamerule." + IFBGameRules.GLASS_BOTTLE + ".description", """
          When enabled, glass bottle can be enchanted with infinity.
          §lInfinity Empty Bottle§r
          - Infinitely empty and discard water in cauldron.
          - Infinitely collect and discard dragon breath.
          - Infinitely collect and discard honey from beehive and bee nest with full honey.
          - Can be inserted with fluid (must have bottle type) by fluid tank of mod. Each time one bottle of fluid. Inserted fluid is voided.
          - Can't be used in crafting.
          """);
        b.add("gamerule." + IFBGameRules.MILK_BUCKET, modded + "Infinity Milk Bucket");
        b.add("gamerule." + IFBGameRules.MILK_BUCKET + ".description", """
          When enabled, milk bucket can be enchanted with infinity.
          §lInfinity Milk Bucket§r
          - Can be infinitely drunk.
          - Doesn't consume in crafting.
          - If there is mod adding fluid to milk bucket, infinity milk bucket can provide infinite fluid.
          """);
        b.add("gamerule." + IFBGameRules.LAVA_BUCKET, modded + "Infinity Lava Bucket");
        b.add("gamerule." + IFBGameRules.LAVA_BUCKET + ".description", """
          When enabled, lava bucket can be enchanted with infinity.
          §lInfinity Lava Bucket§r
          - Infinitely place lava.
          - Infinitely fill cauldron.
          - Infinitely place lava in dispenser.
          - Infinitely provide lava to fluid tank of mod. Each time one bucket of lava.
          - Doesn't consume in crafting.
          - Is infinite fuel in furnace.
          """);
        b.add("gamerule." + IFBGameRules.HONEY_BOTTLE, modded + "Infinity Honey Bottle");
        b.add("gamerule." + IFBGameRules.HONEY_BOTTLE + ".description", """
          When enabled, honey bottle can be enchanted with infinity.
          §lInfinity Honey Bottle§r
          - Can be infinitely drunk.
          - Doesn't consume in crafting.
          - If there is mod adding fluid to honey bottle, infinity honey bottle can provide infinite fluid.
          """);
        b.add(IFBItemTags.INSERTABLE, "Infinite empty container");
        b.add(IFBItemTags.EXTRACTABLE, "Infinite full container");
        b.add(IFBFluidTags.INSERTABLE, "Can be inserted into Infinite empty container");
        b.add(IFBFluidTags.EXTRACTABLE, "Can be extracted from Infinite full container");
        
    }
}
