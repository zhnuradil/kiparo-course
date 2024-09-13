package com.kiparo.deliveryapp.core.di

import kotlin.reflect.KClass

interface GlobalDi {
    fun <T : Any> get(class_: KClass<T>): T
    fun <T : Any> add(key: KClass<T>, object_: T)
}

object DiProvider {
    lateinit var di: GlobalDi
}
