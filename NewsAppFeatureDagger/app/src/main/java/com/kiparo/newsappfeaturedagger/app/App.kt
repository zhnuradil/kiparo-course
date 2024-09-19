/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappfeaturedagger.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.kiparo.newsappfeaturedagger.article_details.di.ArticleDetailsFeatureDepsProvider
import com.kiparo.newsappfeaturedagger.di.DaggerAppComponent
import com.kiparo.newsappfeaturedagger.di.DaggerProvider
import com.kiparo.newsappfeaturedagger.news.di.NewsFeatureDepsProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        initDagger()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        DaggerProvider.appComponent = appComponent
        NewsFeatureDepsProvider.deps = appComponent
        ArticleDetailsFeatureDepsProvider.deps = appComponent
    }
}