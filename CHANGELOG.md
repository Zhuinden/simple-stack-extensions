# Change log

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