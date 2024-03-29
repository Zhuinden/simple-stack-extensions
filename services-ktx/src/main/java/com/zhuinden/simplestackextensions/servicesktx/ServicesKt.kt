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
package com.zhuinden.simplestackextensions.servicesktx

import com.zhuinden.simplestack.*

/**
 * Check if the given service can be found via lookup.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.canFind(serviceTag: String = T::class.java.name): Boolean = canFindService(serviceTag)

/**
 * Look up the service using the provided service tag.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.lookup(serviceTag: String = T::class.java.name): T = lookupService<T>(serviceTag)


/**
 * Check if the given service can be found via lookup from the specified scope, with the specified lookup mode.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.canFindFrom(scopeTag: String, serviceTag: String = T::class.java.name, scopeLookupMode: ScopeLookupMode = ScopeLookupMode.ALL): Boolean = canFindFromScope(scopeTag, serviceTag, scopeLookupMode)

/**
 * Look up the service using the provided service tag from the specified scope, with the specified lookup mode.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.lookupFrom(scopeTag: String, serviceTag: String = T::class.java.name, scopeLookupMode: ScopeLookupMode = ScopeLookupMode.ALL): T = lookupFromScope<T>(scopeTag, serviceTag, scopeLookupMode)

/**
 * Check if the given service can be found via lookup from the specified scope, with the specified lookup mode.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.canFindFrom(scopeKey: ScopeKey, serviceTag: String = T::class.java.name, scopeLookupMode: ScopeLookupMode = ScopeLookupMode.ALL): Boolean = canFindFrom<T>(scopeKey.scopeTag, serviceTag, scopeLookupMode)

/**
 * Look up the service using the provided service tag from the specified scope, with the specified lookup mode.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> Backstack.lookupFrom(scopeKey: ScopeKey, serviceTag: String = T::class.java.name, scopeLookupMode: ScopeLookupMode = ScopeLookupMode.ALL): T = lookupFrom<T>(scopeKey.scopeTag, serviceTag, scopeLookupMode)

/**
 * Add the service to the service binder in the given scope.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> ServiceBinder.add(service: T, serviceTag: String = T::class.java.name) {
    this.addService(serviceTag, service as Any)
}

/**
 * Add the service as an alias to the service binder in the given scope.
 *
 * Uses the fully qualified name of the provided generic type argument as the default tag.
 */
inline fun <reified NAME: Any> ServiceBinder.rebind(service: Any, aliasTag: String = NAME::class.java.name) {
    this.addAlias(aliasTag, service)
}

/**
 * Check if the given service can be found via lookup.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> ServiceBinder.canFind(serviceTag: String = T::class.java.name): Boolean = canFindService(serviceTag)

/**
 * Look up the service from the service binder.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> ServiceBinder.lookup(serviceTag: String = T::class.java.name): T = lookupService(serviceTag)

/**
 * Get the service from the service binder's current scope.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> ServiceBinder.get(serviceTag: String = T::class.java.name): T = getService(serviceTag)

/**
 * Get the service from the service binder's current scope. Returns null if not found.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> ServiceBinder.getOrNull(serviceTag: String = T::class.java.name): T? =
    takeIf { it.hasService(serviceTag) }?.get<T>(serviceTag)

/**
 * Add the provided service to the global services.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> GlobalServices.Builder.add(service: T, serviceTag: String = T::class.java.name): GlobalServices.Builder =
    addService(serviceTag, service as Any)

/**
 * Add the provided service as an alias to the global services.
 *
 * Uses the fully qualified name of the generic type argument as the default tag.
 */
inline fun <reified NAME: Any> GlobalServices.Builder.rebind(service: Any, serviceTag: String = NAME::class.java.name): GlobalServices.Builder =
    addAlias(serviceTag, service)

/**
 * Get the service from the global services.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> GlobalServices.get(serviceTag: String = T::class.java.name): T = getService(serviceTag)

/**
 * Get the service from the global services. Returns null if not found.
 *
 * Uses the fully qualified name of the class as the default tag.
 */
inline fun <reified T: Any> GlobalServices.getOrNull(serviceTag: String = T::class.java.name): T? =
    takeIf { it.hasService(serviceTag) }?.get<T>(serviceTag)
