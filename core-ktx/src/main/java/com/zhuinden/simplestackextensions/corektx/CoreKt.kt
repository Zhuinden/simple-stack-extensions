/*
 * Copyright (C) 2021 Gabor Varadi
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
package com.zhuinden.simplestackextensions.corektx

import com.zhuinden.simplestack.Backstack

/**
 * Check if the given retained object exists.
 *
 * Uses the fully qualified name of the class as the tag.
 */
inline fun <reified T: Any> Backstack.hasRetainedObject(): Boolean = hasRetainedObject(T::class.java.name)

/**
 * Gets the given retained object.
 *
 * Uses the fully qualified name of the class as the tag.
 */
inline fun <reified T: Any> Backstack.getRetainedObject(): T = getRetainedObject(T::class.java.name)

/**
 * Get the given retained object or null.
 *
 * Uses the fully qualified name of the class as the tag.
 */
inline fun <reified T: Any> Backstack.getRetainedObjectOrNull(objectTag: String = T::class.java.name): T? = takeIf { it.hasRetainedObject(objectTag) }?.getRetainedObject(objectTag)

/**
 * Add the retained object.
 *
 * Uses the fully qualified name of the class as the tag.
 */
inline fun <reified T: Any> Backstack.addRetainedObject(retainedObject: T) {
    this.addRetainedObject(T::class.java.name, retainedObject)
}

/**
 * Removes the retained object.
 *
 * Uses the fully qualified name of the class as the tag.
 */
inline fun <reified T: Any> Backstack.removeRetainedObject(): T? = removeRetainedObject(T::class.java.name)