# Saving

## Item Data

Chemical containers use Minecraft's data component system:

```java
ModComponents.CHEM_DATA
```

`ChemData` provides:

- `CODEC` for persistent item data.
- `PACKET_CODEC` for network synchronization.

Because chemical containers store per-stack state, they should stay `maxCount(1)`.

## Block Entity NBT

`ChemMasterBlockEntity` persists:

- `Slots`: list of inventory item stacks.
- `MasterChemicals`: compound of chemical id to amount.

Methods involved:

- `writeNbt(...)`
- `readNbt(...)`
- `toUpdatePacket()`
- `toInitialChunkDataNbt(...)`

After changing slots or chemical storage, call `markDirty()` and `syncToClients(...)` when client UI/state should update.

## Resource Data

Several systems are resource/data-pack driven rather than saved in code:

- Recipes: `data/spacestation/recipe`.
- Loot tables: `data/spacestation/loot_table`.
- Tags: `data/spacestation/tags`.
- Grinding recipes: `data/spacestation/grinding`.
- Reaction recipes: `data/spacestation/reactions`.
- Worldgen configured and placed features: `data/spacestation/worldgen`.

These load from the resource manager and can be overridden by data packs.
