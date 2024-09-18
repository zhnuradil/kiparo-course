package com.kiparo.newsappfeaturedagger.core.navigation

import androidx.fragment.app.Fragment

interface Router {
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean = false)
    fun navigateTo(url: String)
    fun back()
}