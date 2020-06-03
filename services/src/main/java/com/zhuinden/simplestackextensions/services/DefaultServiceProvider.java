package com.zhuinden.simplestackextensions.services;

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
    public void bindServices(@Nonnull ServiceBinder serviceBinder) {
        final Object key = serviceBinder.getKey();

        if(key instanceof HasServices && serviceBinder.getScopeTag().equals(((HasServices) key).getScopeTag())) {
            ((HasServices) key).bindServices(serviceBinder);
        }
    }
}
