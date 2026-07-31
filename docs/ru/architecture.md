# Архитектура

## Обзор

SpaceStation 15 устроен как Fabric-мод с общим/серверным entrypoint и клиентским entrypoint:

- `org.technocracy.spacestation.SpaceStation` инициализирует общие регистры, серверные события, генерацию мира, лут, химию и пакеты.
- `org.technocracy.spacestation.client.SpaceStationClient` инициализирует клиентские экраны, render layers, HUD таймера и клиентские обработчики пакетов.

Mod id: `spacestation`. Для своих идентификаторов используй `Identifier.of(SpaceStation.MOD_ID, name)`.

## Порядок инициализации

`SpaceStation.onInitialize()` сейчас делает следующее:

1. Регистрирует server end tick event и каждый тик обновляет `ActionTimer` для игроков.
2. Регистрирует data components.
3. Регистрирует звуки.
4. Регистрирует предметы.
5. Регистрирует блоки.
6. Регистрирует block entities.
7. Регистрирует screen handlers.
8. Регистрирует reload listener химии.
9. Регистрирует item groups.
10. Регистрирует генерацию мира.
11. Регистрирует изменения лута.
12. Регистрирует пакеты и C2S receiver переноса химикатов.

Порядок важен. Например, химические контейнеры требуют `ModComponents.CHEM_DATA` до инициализации `ChemItems`, а Chem Master block entity зависит от `ModBlocks.CHEM_MASTER_BLOCK`.

## Карта пакетов

- `block`: кастомное поведение блоков, сейчас сборочные блоки и простые crop-блоки.
- `chemistry`: Chem Master, химические данные, контейнеры, рецепты, data component, block entity и screen handler.
- `client`: клиентский entrypoint, HUD и клиентский экран химии.
- `item`: кастомные item-классы и материалы брони.
- `network`: Fabric custom payload'ы и helpers для отправки пакетов.
- `registry`: центральные классы регистрации предметов, блоков, item groups и звуков.
- `registry.items`: предметы по категориям.
- `registry.blocks`: блоки по категориям.
- `system`: общие gameplay-системы, сейчас таймеры действий игрока.
- `world`: генерация руд и изменение loot tables.

## Граница сервер/клиент

Авторитетная gameplay-логика должна жить на сервере. Клиент рисует UI, собирает ввод и отправляет пакеты. Сервер валидирует состояние, меняет inventory/block entity и отправляет клиенту визуальный прогресс.

Chem Master уже работает по этой схеме: клиент отправляет `ChemMovePayload`, сервер проверяет текущий `ChemMasterScreenHandler`, затем переносит химикаты через block entity.
