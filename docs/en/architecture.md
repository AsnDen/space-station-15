# Architecture

## Overview

SpaceStation 15 is organized as a Fabric mod with a shared/server entrypoint and a client entrypoint:

- `org.technocracy.spacestation.SpaceStation` initializes shared registries, server events, world generation, loot modification, chemistry, and packets.
- `org.technocracy.spacestation.client.SpaceStationClient` initializes client screens, render layers, timer HUD, and client packet receivers.

The mod id is `spacestation`. Always build identifiers with `Identifier.of(SpaceStation.MOD_ID, name)` unless referencing vanilla or another namespace.

## Initialization Order

`SpaceStation.onInitialize()` currently performs these steps:

1. Registers a server end-tick event that ticks `ActionTimer` for every online player.
2. Registers data components.
3. Registers sounds.
4. Registers items.
5. Registers blocks.
6. Registers block entities.
7. Registers screen handlers.
8. Registers chemistry reload listeners.
9. Registers item groups.
10. Registers world generation.
11. Registers loot modifiers.
12. Registers packets and the C2S chemistry transfer receiver.

This order matters. For example, chemical containers need `ModComponents.CHEM_DATA` before `ChemItems` are initialized, and the Chem Master block entity depends on `ModBlocks.CHEM_MASTER_BLOCK`.

## Package Map

- `block`: custom block behavior, currently assembly blocks and simple crop blocks.
- `chemistry`: Chem Master machine, chemical data, containers, recipes, data component, block entity, and screen handler.
- `client`: client entrypoint, HUD, and client-only chemistry screen.
- `item`: custom item classes and armor material definitions.
- `network`: Fabric custom payload definitions and packet helpers.
- `registry`: central registration classes for items, blocks, item groups, and sounds.
- `registry.items`: category-specific item declarations.
- `registry.blocks`: category-specific block declarations.
- `system`: generic gameplay systems, currently player action timers.
- `world`: ore generation and loot table modifications.

## Server And Client Boundaries

Keep gameplay authority on the server. Client classes should render UI, collect input, and send packets. Server code should validate state changes, mutate inventories/block entities, and send visual progress packets back to clients.

The current Chem Master follows this model: the client sends `ChemMovePayload`, the server checks that the player still has a `ChemMasterScreenHandler`, and then moves chemicals through the block entity.
