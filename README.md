# Preferences Store

A simple wrapper over DataStore for Jetpack Compose, allowing easier creation of typed configuration classes whose properties are based on MutableState.

## Install

Add it in your settings.gradle.kts at the end of repositories:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
 ```

Step 2. Add the dependency

```kotlin
dependencies {
    implementation("com.github.SomnioNocte:preferences-store:0.1.0")
}
```