/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.news.api

import androidx.fragment.app.Fragment
import com.kiparo.newsappfeaturedagger.news.presentation.NewsFragment
import javax.inject.Inject

class NewsFeatureImpl @Inject constructor() : NewsFeatureApi {
    override fun open(): Fragment = NewsFragment.getInstance()
}