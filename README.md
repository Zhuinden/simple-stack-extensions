# Simple Stack Extensions

Helpers and additional default behaviors for Simple-Stack.


## Using Simple Stack Extensions

In order to use Simple Stack Extensions, you need to add `jitpack` to your project root `build.gradle.kts`
(or `build.gradle`):

``` kotlin
// build.gradle.kts
allprojects {
    repositories {
        // ...
        maven { setUrl("https://jitpack.io") }
    }
    // ...
}
```

or

``` groovy
// build.gradle
allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    // ...
}
```

In newer projects, you need to also update the `settings.gradle` file's `dependencyResolutionManagement` block:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }  // <--
        jcenter() // Warning: this repository is going to shut down soon
    }
}
```


and then, add the dependency to your module's `build.gradle.kts` (or `build.gradle`):

```kotlin
// build.gradle.kts
implementation("com.github.Zhuinden:simple-stack-extensions:2.3.5")

// build.gradle
implementation 'com.github.Zhuinden:simple-stack-extensions:2.3.5'
```

For more fine-grained module selection, you can use:

``` kotlin
// build.gradle.kts
implementation("com.github.Zhuinden.simple-stack-extensions:core-ktx:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:fragments:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:fragments-ktx:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:lifecycle-ktx:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:navigator-ktx:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:services:2.3.5")
implementation("com.github.Zhuinden.simple-stack-extensions:services-ktx:2.3.5")
```

or

``` groovy
// build.gradle
implementation 'com.github.Zhuinden.simple-stack-extensions:core-ktx:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:fragments:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:fragments-ktx:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:lifecycle-ktx:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:navigator-ktx:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:services:2.3.5'
implementation 'com.github.Zhuinden.simple-stack-extensions:services-ktx:2.3.5'
```

## What does it do?

Provides defaults for fragment navigation and screen-level scoping.

Check out the `example` app, based on the FTUE sample from Tutorial Step 9.

## License

    Copyright 2017-2025 Gabor Varadi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
