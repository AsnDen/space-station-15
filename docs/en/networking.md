# Networking

## Packet Definitions

All custom payload records are in `ModPackets`:

- `TimerStartPayload(int totalTicks, boolean isDisassembly)`: S2C.
- `TimerCancelPayload()`: S2C.
- `TimerCompletePayload(boolean isDisassembly)`: S2C.
- `ChemMovePayload(String chem, double amount, boolean toContainer)`: C2S.

Each payload has an `ID`, `CODEC`, and `getId()` implementation.

## Registration

`ModPackets.register()` registers S2C timer payloads:

```java
PayloadTypeRegistry.playS2C().register(...);
```

The C2S chemistry payload is registered in `SpaceStation.onInitialize()`:

```java
PayloadTypeRegistry.playC2S().register(ModPackets.ChemMovePayload.ID, ModPackets.ChemMovePayload.CODEC);
```

## Server Receivers

The Chem Master receiver is registered globally on the server. It executes on the server thread and checks the current screen handler:

```java
if (player.currentScreenHandler instanceof ChemMasterScreenHandler handler) {
    ...
}
```

Keep this pattern for future C2S packets: never trust client state, always re-check the server-side context.

## Client Receivers

`TimerHud.register()` registers client receivers for timer start, cancel, and completion packets. Those packets update local HUD state only.

## Packet Guidelines

- Keep payloads small and explicit.
- Put validation on the server.
- Use ids under the `spacestation` namespace.
- Register the payload type before sending the packet.
- For block-related packets, send enough data to locate or validate the block entity.
