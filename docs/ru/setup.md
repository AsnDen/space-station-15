# Настройка

## Требования

- Java 21.
- Minecraft 1.21.1.
- Fabric Loader `0.18.6` или новее.
- Fabric API `0.116.10+1.21.1`.
- Gradle wrapper из этого репозитория.

Версии лежат в `gradle.properties`. Сборка использует `net.fabricmc.fabric-loom-remap`, `Yarn mappings` и `split environment source sets`.

## Основные команды

Windows PowerShell (или cmd):

```powershell
.\gradlew.bat build
.\gradlew.bat runClient
.\gradlew.bat runServer
```

Unix-подобные shell:

```bash
./gradlew build
./gradlew runClient
./gradlew runServer
```

## Source Sets

- `src/main/java`: общий и серверный код мода.
- `src/client/java`: только клиентский код: экраны, UI, `render layers`, `client init`.
- `src/main/resources`: `fabric.mod.json`, ассеты, рецепты, лут тейблы, тэги, ворлдген, химия.
- `src/client/resources`: mixin.

## Результат сборки

После `build` файл мода появляется в `build/libs`. (плюс в той-же директории появляются библиотеки, их можно удалять)

## IDE

Использовать Java 21 как project SDK. 
Проект лучше импортировать через Gradle.
