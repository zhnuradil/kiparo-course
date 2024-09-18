/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.presentation.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager

interface NavigatorHolder {
    fun manager(): FragmentManager
    fun context(): Context
}