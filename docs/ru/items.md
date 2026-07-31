# Предметы

## Категории предметов

Предметы разделены по категориям в `src/main/java/org/technocracy/spacestation/registry/items`:

- `FoodItems`: еда, ингредиенты, пиццы, бургеры, сыр и другие съедобные предметы.
- `ToolItems`: ломы, отвертка, сварка, гаечный ключ, omnitool, кухонный нож, пластиковый нож.
- `MiscItems`: материалы, руды как предметы, крафтовые ингредиенты, ID, пластик, сталь, пласталь, стержни.
- `ChemItems`: beaker и canister.
- `PlantItems`: семена и урожай.
- `PlushieItems`: пищащие плюшевые игрушки.
- `ArmorItems`: части брони nukeops.
- `DrinkItems`: питьевые предметы, например enzyme.

## Как добавить простой предмет

1. Выбери подходящий category-файл или создай новый, если семейство большое.
2. Зарегистрируй предмет через `Registry.register(Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "id"), item)`.
3. Добавь предмет в `ModItemGroups.CONTENT`, если он должен быть в creative tab.
4. Добавь assets:
   - `assets/spacestation/models/item/<id>.json`
   - `assets/spacestation/textures/item/.../<id>.png`
   - ключи в `assets/spacestation/lang/en_us.json` и `ru_ru.json`
5. Добавь рецепты, tags или loot data при необходимости.

## Химические контейнеры

Химические контейнеры являются экземплярами `ChemContainer`. Они используют data component `ModComponents.CHEM_DATA`:

- `ChemData.EMPTY_BEAKER`: вместимость `100.0`.
- `ChemData.EMPTY_CANISTER`: вместимость `200.0`.

Контейнеры имеют stack size 1, потому что каждый stack хранит свою химическую смесь.

## Кастомное поведение

- `ChemContainer` можно использовать, чтобы выпить химикаты. Он снимает по `5.0` units и накладывает status effects.
- `SqueakyPlushieItem` проигрывает настроенные звуки.
- `KnifeItem` отвечает за поведение ножей и durability.
- `NukeopsArmorItem` представляет кастомные части брони.

## Нейминг

Registry id: lowercase snake_case. Java-поля: uppercase with underscores. Translation key должен соответствовать id:

```text
item.spacestation.example_item
```
