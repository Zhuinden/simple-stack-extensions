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

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

import javax.annotation.Nonnull;

/**
 * A default base fragment that contains a method to get the key from the arguments.
 */
public abstract class KeyedFragment extends Fragment {
    /**
     * Required no-arg fragment constructor.
     */
    public KeyedFragment() {
        super();
    }

    /**
     * Optional fragment constructor that takes layout.
     *
     * @param layoutRes the layout res
     */
    public KeyedFragment(@LayoutRes int layoutRes) {
        super(layoutRes);
    }

    /**
     * Returns the key added to the arguments of the Fragment.
     *
     * @param <T> the type of the key
     * @return the key
     */
    @Nonnull
    public final <T extends DefaultFragmentKey> T getKey() {
        final T key = requireArguments().getParcelable(DefaultFragmentKey.ARGS_KEY);

        if(key == null) {
            throw new NullPointerException("The key provided as fragment argument should not be null!");
        }

        return key;
    }
}