# Setup

## Requirements

- Java 21.
- Minecraft 1.21.1.
- Fabric Loader `0.18.6` or newer.
- Fabric API `0.116.10+1.21.1`.
- Gradle wrapper from this repository.

Version values live in `gradle.properties`. The Gradle build uses `net.fabricmc.fabric-loom-remap`, Yarn mappings, and split environment source sets.

## Common Commands

Windows PowerShell:

```powershell
.\gradlew.bat build
.\gradlew.bat runClient
.\gradlew.bat runServer
```

Unix-like shells:

```bash
./gradlew build
./gradlew runClient
./gradlew runServer
```

## Source Sets

- `src/main/java`: shared and server-side mod logic.
- `src/client/java`: client-only screens, HUD, render layers, and client initialization.
- `src/main/resources`: `fabric.mod.json`, assets, recipes, loot tables, tags, worldgen data, chemistry data.
- `src/client/resources`: client mixin configuration.

## Build Output

The mod jar is produced under `build/libs` after `build`.

## IDE Notes

Use Java 21 as the project SDK. Import the project from Gradle rather than manually creating a Java project.
