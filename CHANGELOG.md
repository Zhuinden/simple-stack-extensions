# Change log

-Simple Stack Extensions 2.3.5 (2025-12-25)
--------------------------------

- Update simple-stack to 2.9.1.

-Simple Stack Extensions 2.3.4 (2024-05-06)
--------------------------------

- Update simple-stack to 2.9.0.

-Simple Stack Extensions 2.3.3 (2023-07-03)
--------------------------------

- Update simple-stack to 2.8.0.

No other changes. 

-Simple Stack Extensions 2.3.2 (2023-04-15)
--------------------------------

- Add `Fragment.lookupFrom(scopeTag, ...)` to `fragments-ktx`.

-Simple Stack Extensions 2.3.1 (2023-04-15)
--------------------------------

- Add `Backstack.canFindFrom(scopeTag, ...)` and `Backstack.lookupFrom(scopeTag, ...)` to `services-ktx`.

-Simple Stack Extensions 2.3.0 (2023-03-31)
--------------------------------

- Update simple-stack to 2.7.0.
- 
- New artifact: `lifecycle-ktx`.

Currently, it allows lifecycle-aware observing of `AheadOfTimeWillHandleBackChangedListener` on a `Backstack`.

It changes the code from this:

```kotlin
    backstack.addAheadOfTimeWillHandleBackChangedListener(updateBackPressedCallback)
}

override fun onDestroy() {
    super.onDestroy()
    backstack.removeAheadOfTimeWillHandleBackChangedListener(updateBackPressedCallback);
}
```

to this:

```kotlin
backstack.observeAheadOfTimeWillHandleBackChanged(this, backPressedCallback::setEnabled)
```

- Update transitive AndroidX dependencies.

- Update Kotlin to 1.8.10.


-Simple Stack Extensions 2.2.5 (2022-11-11)
--------------------------------

- Update simple-stack to 2.6.5.

- Update transitive AndroidX dependencies.

- Update Kotlin to 1.7.10.

AndroidX demands compileSdkVersion 33, so the library is also updated to compileSdkVersion 33. 

-Simple Stack Extensions 2.2.4 (2022-04-21)
--------------------------------

- Update simple-stack to 2.6.4.

- Update Kotlin to 1.5.32.

- Replace `android-maven` plugin with `maven-publish`.

- Update transitive AndroidX dependencies.

AndroidX demands compileSdkVersion 31, so the library also updated to compileSdkVersion 31.

- 2.2.3 had an issue with `maven-publish` and is therefore skipped.

-Simple Stack Extensions 2.2.2 (2021-06-07)
--------------------------------
- No significant changes.

- Update simple-stack to 2.6.2.

-Simple Stack Extensions 2.2.1 (2021-05-06)
--------------------------------
- No significant changes.

- Update simple-stack to 2.6.1.

- Update Kotlin to 1.4.32.

-Simple Stack Extensions 2.2.0 (2021-03-08)
--------------------------------
- Added `core-ktx` module with the following new extensions:

  - `Backstack.addRetainedObject()`
  - `Backstack.removeRetainedObject()`
  - `Backstack.hasRetainedObject()`
  - `Backstack.getRetainedObject()`
  - `Backstack.getRetainedObjectOrNull()`

Easiest to use with: `import com.zhuinden.simplestackextensions.corektx.*`

Core-ktx depends on features added in `simple-stack 2.6.0`, so `simple-stack-extensions 2.2.0` requires `simple-stack 2.6.0`.

- Make generics have bound as `: Any` (instead of implicit `: Any?`).

- Update Kotlin to 1.4.30.

- Update to simple-stack 2.6.0.

-Simple Stack Extensions 2.1.0 (2020-12-18)
--------------------------------
- Added `GlobalServices.get()` and `GlobalServices.getOrNull()` to `services-ktx`.

- Added `configureFragmentTransaction()` method to `DefaultFragmentStateChanger`, making it more customizable.

- Added `example-sharedelements` using `configureFragmentTransaction()` which handles a shared element transaction with fragments.

- Added some missing `@Nonnull`s in `DefaultFragmentKey` and `KeyedFragment`.

- Update Kotlin to 1.4.21.

- Update to simple-stack 2.5.0.

-Simple Stack Extensions 2.0.1 (2020-10-05)
--------------------------------
- `services` now depends on `androidx.annotation.annotation:1.1.0`.

- Add `@CallsSuper` to `DefaultServiceProvider.bindServices()` in `services`.

- Fix in `fragments`, that when a FragmentTransaction was being executed (for example directly in `onViewCreated`), trying to run another FragmentTransaction would throw an (undocumented) IllegalStateException.

Previously, this would have needed a `handler.post {` on the user's side, but this isn't the user's responsibility.

Now, the `DefaultFragmentStateChanger` postpones the FragmentTransaction for the next event loop.

- Add `Activity.androidContentFrame` to `navigator-ktx`.

-Simple Stack Extensions 2.0.0 (2020-06-03)
--------------------------------
- Initial release.

- Built against simple-stack 2.3.2.
