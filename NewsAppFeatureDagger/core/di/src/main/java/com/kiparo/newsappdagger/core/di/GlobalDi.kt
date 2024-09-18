/*
 * Copyright (c) 2023. Kiparo.com
 */

@file:Suppress("LocalVariableName")

package com.kiparo.newsappdagger.core.di

import kotlin.reflect.KClass

interface GlobalDI {
    fun <T : Any> get(class_: KClass<T>): T
    fun <T : Any> add(key: KClass<T>, object_: T)
}

interface KiparoDiComponent {
    fun getKiparoDi(): GlobalDI = DiProvider.di
}

inline fun <reified T : Any> kiparoDi(): Lazy<T> =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) { DiProvider.di.get(T::class) }
