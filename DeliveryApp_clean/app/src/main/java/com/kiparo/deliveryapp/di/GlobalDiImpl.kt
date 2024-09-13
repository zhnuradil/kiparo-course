package com.kiparo.deliveryapp.di

import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class GlobalDiImpl : com.kiparo.deliveryapp.core.di.GlobalDi {
    private val classes = mutableMapOf<Any, Any>()

    override fun <T : Any> get(class_: KClass<T>): T {
        if (classes[class_] != null) {
            return classes[class_] as T
        } else {
            throw ClassNotFoundException("Instance of $class_ not found")
        }
    }

    override fun <T : Any> add(key: KClass<T>, object_: T) {
        classes[key] = object_
    }

}