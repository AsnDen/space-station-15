# Events And Timers

## Server Tick Event

`SpaceStation.onInitialize()` registers `ServerTickEvents.END_SERVER_TICK`. Every server tick, it iterates online players and calls:

```java
ActionTimer.tick(player);
```

## ActionTimer

`ActionTimer` stores active actions by player UUID. Each action contains:

- Target block position.
- Player start position.
- Total ticks.
- Remaining ticks.
- Completion callback.
- Whether the action is disassembly.

The timer cancels if the player moves more than `0.2` blocks from the start position. On completion, it runs the callback and sends a completion packet.

## Timer HUD

`TimerHud` is client-only. It listens for timer packets and renders a progress bar near the bottom center of the screen:

- Blue: assembly.
- Orange: disassembly.
- Green flash: completed.
- Red flash: cancelled.

## Loot Table Events

`ModLootModifiers.register()` uses `LootTableEvents.MODIFY` to add crop seeds to selected vanilla chest loot tables.

The helper `addSeedLoot(...)` receives a map of loot-table groups to chance values and appends a pool when the current table matches.

## Adding New Timed Actions

Use `ActionTimer.start(...)` from server-side code. The completion callback should re-check any important state before mutating the world or inventory, because conditions may have changed while the timer was running.
