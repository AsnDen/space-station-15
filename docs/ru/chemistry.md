# Химия

## Главные понятия

Химическая система data-driven и строится вокруг:

- `ChemData`: immutable карта химикатов плюс вместимость.
- `ChemContainer`: item, который хранит `ChemData`.
- `ChemRegistry`: reload listener для загрузки grinding и reaction JSON.
- `ChemReactor`: применяет подходящие реакции к смеси.
- `ChemMasterBlockEntity`: inventory машины и внутреннее хранилище химикатов.
- `ChemMasterScreen` и `ChemMasterScreenHandler`: клиентский UI и серверная container-логика.

## ChemData

`ChemData` хранит:

- `Map<String, Double> chemicals`
- `double capacity`

Важные методы:

- `totalVolume()`
- `freeSpace()`
- `add(chem, amount)`
- `remove(chem, amount)`
- `has(chem, amount)`

`add(...)` вызывает `ChemReactor.react(...)`, поэтому реакции могут происходить сразу при добавлении химиката в контейнер.

## Grinding Recipes

Что-бы получить химикаты, надо сначала дробить предметы.

Рецепты дробления лежат здесь:

```text
src/main/resources/data/spacestation/grinding/<id>.json
```

Формат:

```json
{
  "ingredient": "minecraft:iron_ingot",
  "results": {
    "iron": 1.0
  }
}
```

Chem Master проверяет slot `0` каждые `10` серверных тиков и конвертирует один предмет, если есть рецепт.

## Reaction Recipes

Химикаты могут смешиваться и создавать реакции.

Рецепты реакций лежат здесь:

```text
src/main/resources/data/spacestation/reactions/<id>.json
```

Предпочтительный явный формат:

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

`ChemRegistry` также поддерживает bulk table format: верхний ключ является результатом, а вложенный объект - картой реагентов.
(Неиспользуемое и мусорное, пж не используйте)

## Chem Master

У Chem Master два слота:

- Slot `0`: вход для дробления.
- Slot `1`: химический контейнер, только `ChemContainer`.

Block entity хранит отдельную карту `masterChemicals`. Клиент показывает хранилище машины и содержимое контейнера двумя scrollable списками.

Перенос идет через `ModPackets.ChemMovePayload`:

- `toContainer = true`: из хранилища машины в контейнер.
- `toContainer = false`: из контейнера в хранилище машины.

## Употребление химикатов

При использовании химического контейнера сервер снимает `5.0` units с каждого химиката, где есть хотя бы это количество, и накладывает status effect. Для многих химикатов пока стоят placeholder effects; точное поведение добавляется в `ChemContainer.applyChemEffect(...)`.
