# Change log

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
