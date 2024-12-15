/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.pizzaapp.app.di

import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class GlobalDiImpl : GlobalDi {
    private val diContainer : MutableMap<Any, Any> = mutableMapOf()

    override fun <T : Any> get(class_: KClass<T>): T {
        if(diContainer.containsKey(class_)){
            return diContainer[class_] as T
        }
        throw IllegalStateException("$class_ not found in diContainer")
    }

    override fun <T : Any> add(key: KClass<T>, object_: T): GlobalDi {
        diContainer[key] = object_
        return this
    }
}