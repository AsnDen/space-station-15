# Генерация мира

## Регистрация в коде

`ModWorldGeneration.init()` добавляет placed features в Overworld biomes:

- `uranium_ore_block`
- `plasma_ore_block`
- `bananium_ore_block`
- `telecrystal_crystal_block`

Все четыре добавляются в:

```java
BiomeSelectors.foundInOverworld()
GenerationStep.Feature.UNDERGROUND_ORES
```

## JSON Data

Для каждой генерируемой руды нужны:

```text
data/spacestation/worldgen/configured_feature/<id>.json
data/spacestation/worldgen/placed_feature/<id>.json
```
Без ворлдгена, руды не будут спавниться в мире.

Код ссылается на `placed feature id` через `RegistryKey<PlacedFeature>`.

## Как добавить новую руду

1. Зарегистрировать блок в `ModBlocks`.
2. Добавить блокстейт, модельку блока, модельку айтемы, текстуру, лут тейблы.
3. Добавить configured feature JSON.
4. Добавить placed feature JSON.
5. Добавить `RegistryKey<PlacedFeature>` в `ModWorldGeneration`.
6. Добавить ворлдген через `BiomeModifications.addFeature(...)`.

Можно использовать уже существующую руду как шаблон и менять id, блокстейты, placement values и generation rules.
