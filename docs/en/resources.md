# Resources

## Directory Map

Main resources live under:

```text
src/main/resources
```

Important folders:

- `assets/spacestation/lang`: translations.
- `assets/spacestation/models/item`: item models.
- `assets/spacestation/models/block`: block models.
- `assets/spacestation/blockstates`: blockstate definitions.
- `assets/spacestation/textures`: textures.
- `assets/spacestation/sounds`: sound files.
- `data/spacestation/recipe`: crafting and smelting recipes.
- `data/spacestation/loot_table`: block loot tables.
- `data/spacestation/tags`: item and block tags.
- `data/spacestation/worldgen`: configured and placed features.
- `data/spacestation/grinding`: chemistry grinding recipes.
- `data/spacestation/reactions`: chemistry reaction recipes.

## Translation Keys

Keep all language files synchronized:

```text
assets/spacestation/lang/en_us.json
assets/spacestation/lang/ru_ru.json
```

Common keys:

- `item.spacestation.<id>`
- `block.spacestation.<id>`
- `itemGroup.spacestation.content`
- `gui.spacestation.chem_master.<key>`
- `chem.spacestation.<chemical_id>`

## Model And Texture Naming

Use the same registry id for the item model file. Block models may be grouped by type, such as `block/wall`, `block/tile`, `block/plant`, or `block/ores`.

Keep ids lowercase snake_case.

## Tags

Project tags currently include item tags such as:

- `spacestation:knives`
- `spacestation:nukeops_repair`

There is also a vanilla block tag extension:

- `minecraft:mineable/pickaxe`

Add tags when multiple items or blocks need the same behavior in recipes, loot, or code.
