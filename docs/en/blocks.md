# Blocks

## Block Categories

`ModBlocks` currently registers:

- Ore/resource blocks: `uranium_ore_block`, `plasma_ore_block`, `bananium_ore_block`, `telecrystal_block`, `telecrystal_crystal_block`.
- Construction blocks: `wall_girder`, `wall_girder_reinforced`, `steel_wall`, `steel_wall_reinforced`, `steel_tile`.
- Machine blocks: `chem_master_block`.
- Plants: `PlantBlocks`

## Adding A Basic Block

1. Add a field in `ModBlocks`.
2. Use the private `register(...)` helper so both the block and optional block item are registered.
3. Add blockstate JSON under `assets/spacestation/blockstates`.
4. Add block model JSON under `assets/spacestation/models/block`.
5. Add item model JSON under `assets/spacestation/models/item`.
6. Add textures under `assets/spacestation/textures/block`.
7. Add loot table under `data/spacestation/loot_table/blocks`.
8. Add recipes, tags, and translations if needed.

## Assembly Blocks

`AssemblyBlock` supports in-game block upgrades and optional disassembly. Recipes are registered in code:

```java
AssemblyBlock.registerUpgrade(source, material, result, cost, assemblyTime, disassemblyTime, tools...);
```

Arguments:

- `source`: block being clicked.
- `material`: item consumed during assembly.
- `result`: block placed after the timer completes.
- `cost`: amount of material consumed.
- `assemblyTime`: seconds required to assemble.
- `disassemblyTime`: seconds required to disassemble.
- `tools`: optional tools that allow disassembly.

Assembly and disassembly use `ActionTimer`, so the player must stay still until completion.

## Crops

`SimpleCropBlock` extends `CropBlock` and receives a seed supplier. This avoids static initialization problems between crops and seed items.

Crop models currently use staged models and textures in:

```text
assets/spacestation/models/block/plant/<crop>/stageN.json
assets/spacestation/textures/block/plant/<crop>/stageN.png
```

## Block Entities

Only Chem Master currently has a block entity. Its inventory and chemical storage are persisted in NBT by `ChemMasterBlockEntity`.
