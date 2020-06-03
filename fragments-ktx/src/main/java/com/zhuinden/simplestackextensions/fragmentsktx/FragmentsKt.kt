package com.zhuinden.simplestackextensions.fragmentsktx

import androidx.fragment.app.Fragment
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.navigator.Navigator

/**
 * Returns the backstack via Navigator.
 *
 * Requires that the fragment is currently added, Navigator is installed, and the Activity is not finishing.
 */
val Fragment.backstack: Backstack
    get() = Navigator.getBackstack(requireContext())

/**
 * Look up the service through the backstack of the fragment.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T> Fragment.lookup(serviceTag: String = T::class.java.name): T =
    backstack.lookupService(serviceTag)
