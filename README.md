# ULesson Test
## Features 🎨

- **100% Kotlin-only project**.
- Libraries:
  - Coroutine 
  - Jetpack compose 
  - Koin
  - AndroidX
  - MVVM
  - JUnit tests
  - Ktor
  - Compose Destinations
  - Kotlinx Serialization
  - Lottie
- Kover plugin to check test coverage
- 100% Gradle Kotlin DSL setup.
- Kotlin Static Analysis via `ktlint`.

## Gradle Setup 🐘

This project is using [**Gradle Kotlin DSL**](https://docs.gradle.org/current/userguide/kotlin_dsl.html) as well as the [Plugin DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block) and gradle version catalogs to setup the build.

Dependencies are specified in the [libs.version.toml](gradle/libs.versions.toml) file. This provides convenient auto-completion when writing your gradle files.

## Static Analysis 🔍

This project is using [**ktlint**](https://github.com/pinterest/ktlint) to format your code. To reformat all the source code as well as the buildscript you can run
```bash
# Run lint and display issues
./ktlint
```


To fix the issues, run:
```bash
# Run lint and try to fix issues automatically (not all issues are fixable this way)
./ktlint -F
```


## Test Coverage 🔍

This project is using [**Kotlin Kover**](https://github.com/Kotlin/kotlinx-kover) to check test coverage. To generate the test coverage report you can run
```bash
# Generate test coverage report
./gradlew koverHtmlReport
```


## How To Build
Pull the code on this branch, import into Android Studio, from there you can run it like a standard
android project project or run ./gradlew assembleDebug. Further notes can be found here https://developer.android.com/studio/build/building-cmdline#DebugMode