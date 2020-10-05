/*
 * Copyright (C) 2020 Gabor Varadi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
