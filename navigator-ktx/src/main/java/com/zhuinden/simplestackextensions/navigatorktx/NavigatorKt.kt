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
package com.zhuinden.simplestackextensions.navigatorktx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.Window
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

/**
 * Returns the content frame of the Activity.
 */
val Activity.contentFrame: ViewGroup
    get() = findViewById(Window.ID_ANDROID_CONTENT)