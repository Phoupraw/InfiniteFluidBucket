# Change Log
[中文](./CHANGELOG.md)
[English](./CHANGELOG-en.md)
# 0.1.0
## New
- Water bucket can be enchanted with infinity, allowing infinite water.
- Bucket can be enchanted with infinity, allowing infinitly discarding fluid.
# 0.2.0
## New
- Milk bucket can be enchanted with infinity, allowing infintely drinking.
- Config file and config screen, needing _Yet Another Config Lib_ as library.
## Fix
- Infinity empty bucket can milk, converting to enchantmentless milk bucket.
- Infinity empty bucket can empty fluid from cauldron, converting to enchantmentless fluid bucket.
- Infinity water bucket can fill cauldron, converting to enchantmentless empty bucket.
# 0.3.0
## New
- Glass bottle can be enchanted with infinity, allowing infinitly discarding fluid.
- Water potion can be enchanted with infinity, providing infinite water.
## Fix
- Even if infinity bucket isn't enabled in config, bucket can also be added with enchantment by anvil.
- Infinity empty bucket can be used in crafting and won't consume.
- Infinity empty bucket can receive water from wet sponge in furnace and convert to water bucket without enchantment.
# 0.3.1
## New
- Github link.
- MCMOD link.
# 0.3.2
## Fix
- Enchantmentless empty bucket can't accept water from wet sponge in furnace. Infinity empty bucket can accept water from wet sponge in furnace and turns into enchantmentless water bucket.
# 0.3.3
## Fix
- Incompitible with VMP (`com.ishland.vmp.mixins.general.ingredient_matching.MixinIngredient`).
- In shapeless recipes, infinity empty bucket and infinity glass bottle can be used as ingredient.
# 0.3.4
## Fix
- Infinity empty bucket can receive less than one bucket of fluid once; infinigy water bucket can provide less than one bucket of water once.
# 0.4.0
## New
- Add items enchanted with infinity to creative inventory.
## Change
- Infinity empty bucket can only be inserted with fluid that has bucket type.
- Infinity glass bottle can only be inserted with fluid that has bottle type.
- Add item sprites to config screen.
- No longer depends on _Fabirc Language Kotlin_.
# 0.4.2
## Fix
- (1.20.1 only) Crash on launch.