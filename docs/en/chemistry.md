# Chemistry

## Main Concepts

The chemistry system is data-driven and centered around:

- `ChemData`: immutable chemical map plus capacity.
- `ChemContainer`: item that stores `ChemData`.
- `ChemRegistry`: reload listener that loads grinding and reaction JSON.
- `ChemReactor`: applies matching reactions to a `ChemData` mixture.
- `ChemMasterBlockEntity`: machine inventory and master chemical storage.
- `ChemMasterScreen` and `ChemMasterScreenHandler`: client UI and server container logic.

## ChemData

`ChemData` stores:

- `Map<String, Double> chemicals`
- `double capacity`

Important methods:

- `totalVolume()`
- `freeSpace()`
- `add(chem, amount)`
- `remove(chem, amount)`
- `has(chem, amount)`

`add(...)` triggers `ChemReactor.react(...)`, so reactions can happen immediately when a chemical enters a container.

## Grinding Recipes

To get chemicals, you should grind items first.

Grinding recipes live in:

```text
src/main/resources/data/spacestation/grinding/<id>.json
```

Format:

```json
{
  "ingredient": "minecraft:iron_ingot",
  "results": {
    "iron": 1.0
  }
}
```

The Chem Master checks slot `0` every `10` server ticks and converts one input item if a recipe exists.

## Reaction Recipes

Сhemicals react with each other.

Reaction recipes live in:

```text
src/main/resources/data/spacestation/reactions/<id>.json
```

Preferred explicit format:

```json
{
  "reagents": {
    "hydrogen": 2.0,
    "oxygen": 1.0
  },
  "results": {
    "water": 1.0
  },
  "min_volume": 3.0
}
```

`ChemRegistry` also supports a bulk table format where each top-level key is the produced chemical and the nested object is the reagent map.
(Unused and garbage, pls don't use)

## Chem Master

The Chem Master block has two slots:

- Slot `0`: grinding input.
- Slot `1`: chemical container, limited to `ChemContainer` items.

The block entity keeps a separate `masterChemicals` map. The client displays master storage and container storage in two scrollable lists.

Transfers use `ModPackets.ChemMovePayload`:

- `toContainer = true`: move from machine storage to container.
- `toContainer = false`: move from container to machine storage.

## Drinking Chemicals

Using a chemical container on the server removes `5.0` units from each chemical that has at least that amount, then applies a status effect. Many chemicals currently use placeholder effects; add specific behavior in `ChemContainer.applyChemEffect(...)`.
