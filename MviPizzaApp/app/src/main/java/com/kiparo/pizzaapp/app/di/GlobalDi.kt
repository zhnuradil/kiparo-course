/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.pizzaapp.app.di

import kotlin.reflect.KClass

interface GlobalDi {
    fun <T : Any> get(class_: KClass<T>): T
    fun <T : Any> add(key: KClass<T>, object_: T): GlobalDi
}

object DiProvider {
    lateinit var di: GlobalDi
}