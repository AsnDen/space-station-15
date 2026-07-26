# Сохранение

## Данные предметов

Химические контейнеры используют систему `data components Minecraft`:

```java
ModComponents.CHEM_DATA
```

`ChemData` предоставляет:

- `CODEC` для сохранения данных предмета.
- `PACKET_CODEC` для сетевой синхронизации.

Так как контейнеры хранят состояние конкретного стака, они должны оставаться `maxCount(1)`.

## NBT

`ChemMasterBlockEntity` сохраняет:

- `Slots`: список стаков.
- `MasterChemicals`: база, где `chemical id` связан с `amount`.

Методы:

- `writeNbt(...)`
- `readNbt(...)`
- `toUpdatePacket()`
- `toInitialChunkDataNbt(...)`

После изменения слотов или химического хранилища вызывай `markDirty()` и `syncToClients(...)`, если клиентский UI/state должен обновиться.

## Дата Ресурсов

Часть систем хранится в datapack/resource данных, а не в коде:

- Рецепты: `data/spacestation/recipe`.
- Лут тейблы: `data/spacestation/loot_table`.
- тэги: `data/spacestation/tags`.
- Рецепты дробления: `data/spacestation/grinding`.
- Рецепты реакций: `data/spacestation/reactions`.
- Ворлдген: `data/spacestation/worldgen`.

Эти данные грузятся через менеджер ресурсов и могут быть переопределены датапаками.
