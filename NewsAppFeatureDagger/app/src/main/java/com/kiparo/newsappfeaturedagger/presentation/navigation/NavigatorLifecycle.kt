/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.presentation.navigation

interface NavigatorLifecycle {
    fun onCreate(holder: NavigatorHolder)
    fun onDestroy()
}