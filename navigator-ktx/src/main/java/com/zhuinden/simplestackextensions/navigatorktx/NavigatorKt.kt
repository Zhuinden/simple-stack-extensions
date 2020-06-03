package com.zhuinden.simplestackextensions.navigatorktx

import android.content.Context
import android.view.View
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.navigator.Navigator

/**
 * Returns the backstack via Navigator.
 *
 * Requires that the Navigator is installed, and the Activity is not finishing.
 */
val Context.backstack: Backstack
    get() = Navigator.getBackstack(this)

/**
 * Returns the backstack via Navigator.
 *
 * Requires that the Navigator is installed, and the Activity is not finishing.
 */
val View.backstack: Backstack
    get() = context.backstack