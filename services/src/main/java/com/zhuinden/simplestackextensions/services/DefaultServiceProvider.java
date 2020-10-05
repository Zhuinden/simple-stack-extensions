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
package com.zhuinden.simplestackextensions.services;

import androidx.annotation.CallSuper;

import com.zhuinden.simplestack.ScopeKey;
import com.zhuinden.simplestack.ScopedServices;
import com.zhuinden.simplestack.ServiceBinder;

import javax.annotation.Nonnull;

/**
 * Default service provider that delegates the service binding to the key, if the key implements {@link HasServices}.
 */
public class DefaultServiceProvider
        implements ScopedServices {
    /**
     * Marker interface for a key to describe that it intends to bind services as a scope.
     */
    public interface HasServices
            extends ScopeKey {
        /**
         * Binds the services for the given scope.
         *
         * @param serviceBinder the service binder
         */
        void bindServices(@Nonnull ServiceBinder serviceBinder);
    }

    /**
     * Default implementation that delegates service binding to the key if the scope matches the key's scope.
     *
     * @param serviceBinder the service binder
     */
    @Override
    @CallSuper
    public void bindServices(@Nonnull ServiceBinder serviceBinder) {
        final Object key = serviceBinder.getKey();

        if(key instanceof HasServices && serviceBinder.getScopeTag().equals(((HasServices) key).getScopeTag())) {
            ((HasServices) key).bindServices(serviceBinder);
        }
    }
}
