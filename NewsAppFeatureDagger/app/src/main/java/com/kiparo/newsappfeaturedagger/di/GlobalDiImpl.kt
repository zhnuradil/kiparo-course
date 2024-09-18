/*
 * Copyright (c) 2023. Kiparo.ru
 */

@file:Suppress("LocalVariableName")

package com.kiparo.newsappfeaturedagger.di

import com.kiparo.newsappdagger.core.di.GlobalDI
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")

class GlobalDIImpl: GlobalDI {

    private val classes = mutableMapOf<Any, Any>()
    override fun <T : Any> get(class_: KClass<T>): T {
        if (classes[class_] != null) {
            return classes[class_] as T
        } else {
            throw ClassNotFoundException("Instance of $class_ not found.")
        }
    }

    override fun <T : Any> add(key: KClass<T>, object_: T) {
        classes[key] = object_
    }
}
