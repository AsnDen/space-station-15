# Ресурсы

## Карта директорий

Основные ресурсы лежат здесь:

```text
src/main/resources
```

Важные папки:

- `assets/spacestation/lang`: переводы.
- `assets/spacestation/models/item`: модельки предметов.
- `assets/spacestation/models/block`: модельки блоков.
- `assets/spacestation/blockstates`: блокстейты.
- `assets/spacestation/textures`: текстуры.
- `assets/spacestation/sounds`: звуки.
- `data/spacestation/recipe`: рецепты.
- `data/spacestation/loot_table`: лут тейблы.
- `data/spacestation/tags`: тэги.
- `data/spacestation/worldgen`: ворлдген.
- `data/spacestation/grinding`: рецепты дробления.
- `data/spacestation/reactions`: рецепты химических реакций.

## Переводы

Все файлы переводов нужно держать синхронными:

```text
assets/spacestation/lang/en_us.json
assets/spacestation/lang/ru_ru.json
```

Типовые ключи:

- `item.spacestation.<id>`
- `block.spacestation.<id>`
- `itemGroup.spacestation.content`
- `gui.spacestation.chem_master.<key>`
- `chem.spacestation.<chemical_id>`

## Имена моделей и текстур

Файл модельки предмета должен совпадать с айди регистрации. Модельки блоков могут быть сгруппированы по типу: `block/wall`, `block/tile`, `block/plant`, `block/ores`.

`Registry id` всегда нижний регистр (snake_case).

## Тэги

Сейчас в проекте есть тэги:

- `spacestation:knives`
- `spacestation:nukeops_repair`

Также расширяются ванильные тэги:

- `minecraft:mineable/pickaxe`

Добавлять тэги можно, когда нескольким предметам или блокам нужно одинаковое поведение в рецептах, луте или коде.