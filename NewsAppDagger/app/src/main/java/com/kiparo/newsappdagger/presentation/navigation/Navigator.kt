/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean = false)
    fun navigateTo(url: String)
    fun back()
}