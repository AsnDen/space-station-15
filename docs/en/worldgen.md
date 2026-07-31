# World Generation

## Code Registration

`ModWorldGeneration.init()` adds placed features to Overworld biomes:

- `uranium_ore_block`
- `plasma_ore_block`
- `bananium_ore_block`
- `telecrystal_crystal_block`

All four are added to:

```java
BiomeSelectors.foundInOverworld()
GenerationStep.Feature.UNDERGROUND_ORES
```

## JSON Data

Each generated ore needs:

```text
data/spacestation/worldgen/configured_feature/<id>.json
data/spacestation/worldgen/placed_feature/<id>.json
```

The code references the placed feature id through a `RegistryKey<PlacedFeature>`.

## Adding A New Ore Feature

1. Register the block in `ModBlocks`.
2. Add blockstate, block model, item model, texture, loot table, and tags.
3. Add configured feature JSON.
4. Add placed feature JSON.
5. Add a `RegistryKey<PlacedFeature>` in `ModWorldGeneration`.
6. Add it with `BiomeModifications.addFeature(...)`.

Use an existing ore as the template and change only the id, block state, placement values, and generation rules.
