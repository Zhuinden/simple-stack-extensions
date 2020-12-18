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
package com.zhuinden.simplestackextensions.fragments;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

import javax.annotation.Nonnull;

/**
 * Default fragment key for the {@link DefaultFragmentStateChanger}.
 */
public abstract class DefaultFragmentKey implements Parcelable {
    /**
     * Argument key used in the arguments bundle to store and retrieve the key from the fragment.
     */
    public static final String ARGS_KEY = "___SIMPLE_STACK_FRAGMENT_ARGUMENTS_KEY_TAG___";

    /**
     * Return the fragment tag. Implemented as FQN of the key by default.
     *
     * Should be consistent across process death.
     *
     * In data classes, should be generally overridden to return `toString()`.
     *
     * @return the fragment tag
     */
    @Nonnull
    public String getFragmentTag() {
        return getClass().getName();
    }

    /**
     * Instantiate a new instance of the Fragment.
     *
     * @return the new instance of fragment
     */
    @Nonnull
    protected abstract Fragment instantiateFragment();

    /**
     * Create instance of the fragment, with the key set in its arguments.
     *
     * @return the new fragment instance
     */
    @Nonnull
    public final Fragment createFragment() {
        Fragment fragment = instantiateFragment();
        Bundle args = fragment.getArguments();
        if(args == null) {
            args = new Bundle();
            fragment.setArguments(args);
        }
        args.putParcelable(ARGS_KEY, this);
        return fragment;
    }
}
