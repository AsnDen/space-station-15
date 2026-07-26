# Coding Style

## Java Style

- Use Java 21.
- Use package-private or private helpers where public access is not needed.
- Keep registry ids lowercase snake_case.
- Keep constants `UPPER_SNAKE_CASE`.
- Keep mod identifiers under `SpaceStation.MOD_ID`.
- Prefer small category registries over very large mixed files.

## Fabric Patterns

- Server authority belongs on the server.
- Client code belongs in `src/client/java`.
- Register payload codecs before sending packets.
- Avoid loading client-only classes from common/server code.
- Use data-driven JSON resources for recipes, loot, worldgen, and chemistry data where practical.

## Adding Content Checklist

For an item:

- Java registry field.
- Creative tab entry.
- Model JSON.
- Texture.
- English and Russian translations.
- Recipe, tag, or loot data if needed.

For a block:

- Java registry field.
- Optional `BlockItem`.
- Creative tab entry.
- Blockstate JSON.
- Block model JSON.
- Item model JSON.
- Texture.
- Loot table.
- Mineable/tool tags if needed.
- English and Russian translations.

## Comments

New comments should be UTF-8 and short. Prefer explaining why something exists over restating what the code already says.

## Testing

At minimum, run:

```bash
./gradlew build
```

For gameplay changes, also run the client and verify the feature in-game.
