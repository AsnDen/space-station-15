# Items

## Item Categories

Items are grouped by category under `src/main/java/org/technocracy/spacestation/registry/items`:

- `FoodItems`: food, ingredients, pizzas, burgers, cheese, and other edible items.
- `ToolItems`: crowbars, screwdriver, welder, wrench, omnitool, kitchen knife, plastic knife.
- `MiscItems`: materials, ores as items, crafting ingredients, IDs, plastic, steel, plasteel, rods.
- `ChemItems`: beaker and canister chemical containers.
- `PlantItems`: seeds and produce.
- `PlushieItems`: squeaky plushies.
- `ArmorItems`: nukeops armor pieces.
- `DrinkItems`: drinkable items such as enzyme.

## Adding A Simple Item

1. Pick the right category file or create a new category if the item family is large.
2. Register the item with `Registry.register(Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "id"), item)`.
3. Add the item to `ModItemGroups.CONTENT` if it should appear in the creative tab.
4. Add assets:
   - `assets/spacestation/models/item/<id>.json`
   - `assets/spacestation/textures/item/.../<id>.png`
   - language keys in `assets/spacestation/lang/en_us.json` and `ru_ru.json`
5. Add recipes, tags, or loot data if needed.

## Chemical Containers

Chemical containers are instances of `ChemContainer`. They use the `ModComponents.CHEM_DATA` data component:

- `ChemData.EMPTY_BEAKER`: capacity `100.0`.
- `ChemData.EMPTY_CANISTER`: capacity `200.0`.

Containers are stack-limited to one item because each stack stores its own chemical mixture.

## Custom Item Behavior

- `ChemContainer` can be used to drink chemicals. It removes `5.0` units per chemical and applies status effects.
- `SqueakyPlushieItem` plays configured plushie sounds.
- `KnifeItem` is used for knife behavior and durability.
- `NukeopsArmorItem` represents custom armor pieces.

## Naming Rules

Use lowercase snake_case ids. Keep Java field names uppercase with underscores. Keep translation keys aligned with the registry id:

```text
item.spacestation.example_item
```
