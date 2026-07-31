# Сеть

## Packet Definitions

Все кастомные записи пейлоадов находятся в `ModPackets`:

- `TimerStartPayload(int totalTicks, boolean isDisassembly)`: S2C.
- `TimerCancelPayload()`: S2C.
- `TimerCompletePayload(boolean isDisassembly)`: S2C.
- `ChemMovePayload(String chem, double amount, boolean toContainer)`: C2S.

У каждого пейлоада есть `ID`, `CODEC` и реализация `getId()`.

## Регистрация

`ModPackets.register()` регистрирует S2C таймерные пейлоады:

```java
PayloadTypeRegistry.playS2C().register(...);
```

C2S химический пейлоад регистрируется в `SpaceStation.onInitialize()`:

```java
PayloadTypeRegistry.playC2S().register(ModPackets.ChemMovePayload.ID, ModPackets.ChemMovePayload.CODEC);
```

## Server Receivers

`Receiver Chem Master` зарегистрирован глобально на сервере. Он выполняется на `server thread` и проверяет текущий `screen handler`:

```java
if (player.currentScreenHandler instanceof ChemMasterScreenHandler handler) {
    ...
}
```

Для будущих C2S пакетов придерживайся этой схемы: не доверять клиентскому состоянию и всегда перепроверять server-side.

## Client Receivers

`TimerHud.register()` регистрирует клиентские рециверы для начала, отмены и завершения таймера. Эти пакеты меняют только локальное состояние HUD.

## Правила для пакетов

- Пейлоад должен быть маленьким и явным.
- Валидация должна быть на сервере.
- Айди должны быть в namespace `spacestation`.
- Тип пейлоада должен быть зарегистрирован до отправки.
- Для всего, что касается пакетов блоков - передавать достаточно данных, что-бы можно было найти и проверить энтити.
