# Блоки

## Категории блоков

`ModBlocks` сейчас регистрирует:

- Ресурсные и рудные блоки: `uranium_ore_block`, `plasma_ore_block`, `bananium_ore_block`, `telecrystal_block`, `telecrystal_crystal_block`.
- Строительные блоки: `wall_girder`, `wall_girder_reinforced`, `steel_wall`, `steel_wall_reinforced`, `steel_tile`.
- Машины: `chem_master_block`.
- Растения: `PlantBlocks`

## Как добавить обычный блок

1. Добавь поле в `ModBlocks`.
2. Используй private helper `register(...)`, чтобы зарегистрировать block и optional block item.
3. Добавь blockstate JSON в `assets/spacestation/blockstates`.
4. Добавь block model JSON в `assets/spacestation/models/block`.
5. Добавь item model JSON в `assets/spacestation/models/item`.
6. Добавь текстуры в `assets/spacestation/textures/block`.
7. Добавь loot table в `data/spacestation/loot_table/blocks`.
8. Добавь recipes, tags и переводы при необходимости.

## AssemblyBlock

`AssemblyBlock` поддерживает сборку блоков в мире и опциональную разборку. Рецепты регистрируются в коде:

```java
AssemblyBlock.registerUpgrade(source, material, result, cost, assemblyTime, disassemblyTime, tools...);
```

Аргументы:

- `source`: блок, по которому кликают.
- `material`: предмет, который тратится при сборке.
- `result`: блок после завершения таймера.
- `cost`: количество материала.
- `assemblyTime`: время сборки в секундах.
- `disassemblyTime`: время разборки в секундах.
- `tools`: optional tools, разрешающие разборку.

Сборка и разборка используют `ActionTimer`, поэтому игрок должен не двигаться до завершения.

## Растения

`SimpleCropBlock` расширяет `CropBlock` и получает seed supplier. Это помогает избежать проблем статической инициализации между ростком и предметом семечки.

Модели растений лежат по стадиям:

```text
assets/spacestation/models/block/plant/<crop>/stageN.json
assets/spacestation/textures/block/plant/<crop>/stageN.png
```

## Энтити

Сейчас блок энтити есть только у Хим-Мастера. Инвентарь и химическое хранилище сохраняются в NBT через `ChemMasterBlockEntity`.
