package phoupraw.mcmod.infinite_fluid_bucket.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;
import phoupraw.mcmod.infinite_fluid_bucket.config.TipDialogScreen;
import phoupraw.mcmod.infinite_fluid_bucket.constant.IFBEnchantmentTags;
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
        IFBDataGen.addGameRule(b, IFBGameRules.WATER_BUCKET, "Infinity Water Bucket", """
          When enabled, water bucket can be enchanted with infinity.
          §lInfinity Water Bucket§r
          - Infinitely place water.
          - Infinitely fill cauldron.
          - Infinitely place water in dispenser.
          - Infinitely provide water to fluid tank of mod. Each time one bucket of water.
          - Doesn't consume in crafting.
          - Can't fish.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.EMPTY_BUCKET, "Infinity Empty Bucket", """
          When enabled, bucket can be enchanted with infinity.
          §lInfinity Empty Bucket§r
          - Infinitely scoop and void fluid.
          - Infinitely empty fluid in cauldron, including powder snow.
          - Infinitely scoop and void fluid in dispenser.
          - Can be inserted with fluid (must have bucket type) by fluid tank of mod. Each time one bucket of fluid. Inserted fluid is voided.
          - Can't be used in crafting.
          - Can't milk.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.WATER_POTION, "Infinity Water Potion", """
          When enabled, water potion can be enchanted with infinity.
          §lInfinity Water Potion§r
          - Infinitely fill cauldron.
          - Infinitely convert dirt into mud.
          - Infinitely provide water to fluid tank of mod. Each time one bottle of water.
          - Doesn't consume in crafting.
          - Can't be used in brewing.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.GLASS_BOTTLE, "Infinity Empty Bottle", """
          When enabled, glass bottle can be enchanted with infinity.
          §lInfinity Empty Bottle§r
          - Infinitely empty and discard water in cauldron.
          - Infinitely collect and discard dragon breath.
          - Infinitely collect and discard honey from beehive and bee nest with full honey.
          - Can be inserted with fluid (must have bottle type) by fluid tank of mod. Each time one bottle of fluid. Inserted fluid is voided.
          - Can't be used in crafting.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.MILK_BUCKET, "Infinity Milk Bucket", """
          When enabled, milk bucket can be enchanted with infinity.
          §lInfinity Milk Bucket§r
          - Can be infinitely drunk.
          - Doesn't consume in crafting.
          - If there is mod adding fluid to milk bucket, infinity milk bucket can provide infinite fluid.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.LAVA_BUCKET, "Infinity Lava Bucket", """
          When enabled, lava bucket can be enchanted with infinity.
          §lInfinity Lava Bucket§r
          - Infinitely place lava.
          - Infinitely fill cauldron.
          - Infinitely place lava in dispenser.
          - Infinitely provide lava to fluid tank of mod. Each time one bucket of lava.
          - Doesn't consume in crafting.
          - Is infinite fuel in furnace.
          """);
        IFBDataGen.addGameRule(b, IFBGameRules.HONEY_BOTTLE, "Infinity Honey Bottle", """
          When enabled, honey bottle can be enchanted with infinity.
          §lInfinity Honey Bottle§r
          - Can be infinitely drunk.
          - Doesn't consume in crafting.
          - If there is mod adding fluid to honey bottle, infinity honey bottle can provide infinite fluid.
          """);
        b.add(IFBItemTags.INSERTABLE, modded + "Infinity empty container");
        b.add(IFBItemTags.EXTRACTABLE, modded + "Infinity full container");
        b.add(IFBFluidTags.INSERTABLE, modded + "Fluids that can be inserted into Infinity empty container");
        b.add(IFBFluidTags.EXTRACTABLE, modded + "Fluids that can be extracted from Infinity full container");
        b.add(IFBFluidTags.BUCKET, modded + "Fluids that can be inserted into Infinity bucket");
        b.add(IFBFluidTags.GLASS_BOTTLE, modded + "Fluids that can be inserted into Infinity glass bottle");
        b.add(IFBEnchantmentTags.INFINITIER, modded + "Items with any enchantment in this tag can provide infinite fluid");
        b.add(TipDialogScreen.NO_WORLD, "You have to enter a world to check or change the value of gamerules. You can only read the description of gamerules.");
        b.add(TipDialogScreen.NO_PERMISSION, "You don't have the permission to change the value of gamerules. You can only check the value and the description of gamerules.");
        
    }
}
