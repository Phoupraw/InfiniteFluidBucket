package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import static phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket.ID;

final class English extends FabricLanguageProvider {
    English(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generateTranslations(TranslationBuilder b) {
        b.add(InfiniteFluidBucket.NAME, "Infinite Fluid Bucket");
        b.add("modmenu.descriptionTranslation." + ID, """
          Enchant bucket with infinity!
          §lSynopsis§r
          - Water bucket can be enchanted with infinity, allowing infinite water.
          - Bucket can be enchanted with infinity, allowing infinitly discarding fluid.
          - Milk bucket can be enchanted with infinity, allowing infintely drinking.
          - Glass bottle can be enchanted with infinity, allowing infinitly discarding fluid.
          - Water potion can be enchanted with infinity, providing infinite water.
          §lProfile§r
          Enchant buckets by enchanting table or anvil.
          Each item can be toggled independently by config file or Mod Menu.
          Can be installed only at server side, just client might have a few sync bugs.
          §lTrivia§r
          This mod is inspired by infinite water bucket of Quark mod.
          """);
        b.add("config." + ID + ".waterBucket.desc", """
          When enabled, water bucket can be enchanted with infinity.
          §lInfinity Water Bucket§r
          - Infinitely place water.
          - Infinitely fill cauldron.
          - Infinitely place water in dispenser.
          - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
          - Can't fish.
          """);
        b.add("config." + ID + ".emptyBucket.desc", """
          When enabled, bucket can be enchanted with infinity.
          §lInfinity Empty Bucket§r
          - Infinitely scoop and void fluid.
          - Infinitely empty fluid in cauldron, including powder snow.
          - Infinitely scoop and void fluid in dispenser.
          - Can be inserted with fluid (must have bucket type) by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
          - Can't milk.
          """);
        b.add("config." + ID + ".waterPotion.desc", """
          When enabled, water potion can be enchanted with infinity.
          §lInfinity Water Potion§r
          - Infinitely fill cauldron.
          - Infinitely convert dirt into mud.
          - Infinitely provide water to fluid tank of mod. Each time one bottle of water.
          - Doesn't consume in crafting.
          - Can't be used in brewing.
          """);
        b.add("config." + ID + ".glassBottle.desc", """
          When enabled, glass bottle can be enchanted with infinity.
          §lInfinity Empty Bottle§r
          - Infinitely empty and discard water in cauldron.
          - Infinitely collect and discard dragon breath.
          - Infinitely collect and discard honey from beehive and bee nest with full honey.
          - Can be inserted with fluid (must have bottle type) by fluid tank of mod. Each time one bottle of fluid. Inserted fluid is voided.
          - Can't be used in crafting.
          """);
        b.add("config." + ID + ".milkBucket.desc", """
          When enabled, milk bucket can be enchanted with infinity.
          §lInfinity Milk Bucket§r
          - Can be infinitely drunk.
          """);
    }
}
