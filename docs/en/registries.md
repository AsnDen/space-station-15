# Registries

## Main Registry Flow

Most registration starts from `SpaceStation.onInitialize()`:

- `ModComponents.register()`
- `ModSounds.register()`
- `ModItems.register()`
- `ModBlocks.register()`
- `ModBlockEntities.register()`
- `ModScreenHandlers.register()`
- `ChemRegistry.register()`
- `ModItemGroups.register()`
- `ModWorldGeneration.init()`
- `ModLootModifiers.register()`
- `ModPackets.register()`

## Items

`ModItems` is the high-level item registration entrypoint. The actual item fields are split into category classes under `registry.items`:

- `ArmorItems`
- `ChemItems`
- `DrinkItems`
- `FoodItems`
- `MiscItems`
- `PlantItems`
- `PlushieItems`
- `ToolItems`

`ItemRegistry` contains helper methods for simple items, food, crops, plushies, and chemistry containers.

## Blocks

`ModBlocks` registers blocks. `PlantBlocks` registers crop blocks separately.

`ModBlocks.register()` also registers assembly upgrade recipes with `AssemblyBlock.registerUpgrade(...)`.

## Components

`ModComponents.CHEM_DATA` is a custom item data component storing `ChemData`. It has both a persistent `Codec` and a network `PacketCodec`.

## Block Entities And Screens

`ModBlockEntities.CHEM_MASTER` binds `ChemMasterBlockEntity` to `ModBlocks.CHEM_MASTER`.

`ModScreenHandlers.CHEM_MASTER` is an `ExtendedScreenHandlerType` using `BlockPos.PACKET_CODEC` so the client can open a screen for the correct block entity.

## Sounds

`ModSounds` registers plushie sound events. The matching sound files and event definitions live under `src/main/resources/assets/spacestation`.
