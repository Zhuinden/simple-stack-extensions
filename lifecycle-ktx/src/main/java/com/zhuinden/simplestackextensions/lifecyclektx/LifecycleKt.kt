/*
 * Copyright (C) 2023 Gabor Varadi
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
package com.zhuinden.simplestackextensions.lifecyclektx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.zhuinden.simplestack.AheadOfTimeWillHandleBackChangedListener
import com.zhuinden.simplestack.Backstack

@PublishedApi
internal class LifecycleAwareAheadOfTimeWillHandleBackChangedListener constructor(
    private val backstack: Backstack,
    private val lifecycleOwner: LifecycleOwner,
    private val aheadOfTimeWillHandleBackChangedListener: AheadOfTimeWillHandleBackChangedListener
) : LifecycleEventObserver {
    init {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            lifecycleOwner.lifecycle.addObserver(this)
        }
    }

    private var isActive: Boolean = false
    private var notificationToken: (() -> Unit)? = null

    private fun shouldBeActive(): Boolean {
        return lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }

    private fun disposeObserver() {
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            stopListening()
            disposeObserver()
            return
        }
        checkIfActiveStateChanged(shouldBeActive())
    }

    private fun checkIfActiveStateChanged(newActive: Boolean) {
        if (newActive == isActive) {
            return
        }
        val wasActive = isActive
        isActive = newActive
        val isActive = isActive

        if (!wasActive && isActive) {
            stopListening()
            backstack.addAheadOfTimeWillHandleBackChangedListener(aheadOfTimeWillHandleBackChangedListener)
            notificationToken = {
                backstack.removeAheadOfTimeWillHandleBackChangedListener(aheadOfTimeWillHandleBackChangedListener)
            }
        }

        if (wasActive && !isActive) {
            stopListening()
        }
    }

    private fun stopListening() {
        notificationToken?.invoke()
        notificationToken = null
    }
}

inline fun Backstack.observeAheadOfTimeWillHandleBackChanged(lifecycleOwner: LifecycleOwner, crossinline willHandleBackChangedObserver: (Boolean) -> Unit) {
    LifecycleAwareAheadOfTimeWillHandleBackChangedListener(
        this,
        lifecycleOwner,
        AheadOfTimeWillHandleBackChangedListener { willHandleBack ->
            willHandleBackChangedObserver.invoke(willHandleBack)
        }
    )
}