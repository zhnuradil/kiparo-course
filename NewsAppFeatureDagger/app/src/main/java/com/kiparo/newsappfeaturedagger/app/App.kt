/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappfeaturedagger.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiparo.newsappdagger.core.di.DiProvider
import com.kiparo.newsappfeaturedagger.BuildConfig
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureApi
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureImpl
import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.data.network.api.NewsApi
import com.kiparo.newsappfeaturedagger.data.network.api.NewsApiNetwork
import com.kiparo.newsappfeaturedagger.data.repository.ArticleRepositoryImpl
import com.kiparo.newsappfeaturedagger.di.DaggerAppComponent
import com.kiparo.newsappfeaturedagger.di.DaggerProvider
import com.kiparo.newsappfeaturedagger.di.GlobalDIImpl
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import com.kiparo.newsappfeaturedagger.news.api.NewsFeatureApi
import com.kiparo.newsappfeaturedagger.news.api.NewsFeatureImpl
import com.kiparo.newsappfeaturedagger.presentation.navigation.NavigatorLifecycle
import com.kiparo.newsappfeaturedagger.presentation.navigation.RouterImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        setupDI()
        setupDiData()
        setupDiNavigation()
        setupDiFeatures()

        initDagger()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        DaggerProvider.appComponent = appComponent
    }

    private fun setupDI() {
        DiProvider.di = GlobalDIImpl()

        DiProvider.di.add(
            key = CoroutineDispatcher::class,
            object_ = Dispatchers.IO
        )
    }

    private fun setupDiData() {
        DiProvider.di.add(
            key = Gson::class,
            object_ = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                .create()
        )

        DiProvider.di.add(
            key = NewsApi::class,
            object_ = NewsApiNetwork(
                apiUrl = BuildConfig.KIPARO_BASE_API_URL + BuildConfig.KIPARO_NEWS_API_PATH,
                gson = DiProvider.di.get(Gson::class)
            )
        )

        DiProvider.di.add(
            key = ArticleRepository::class,
            object_ = ArticleRepositoryImpl(
                newsApi = DiProvider.di.get(NewsApi::class),
                dispatcher = DiProvider.di.get(CoroutineDispatcher::class),
            )
        )
    }

    private fun setupDiNavigation() {
        DiProvider.di.add(
            key = RouterImpl::class,
            object_ = RouterImpl()
        )

        DiProvider.di.add(
            key = Router::class,
            object_ = DiProvider.di.get(RouterImpl::class)
        )

        DiProvider.di.add(
            key = NavigatorLifecycle::class,
            object_ = DiProvider.di.get(RouterImpl::class)
        )
    }

    private fun setupDiFeatures() {
        DiProvider.di.add(
            key = NewsFeatureApi::class,
            object_ = NewsFeatureImpl()
        )

        DiProvider.di.add(
            key = ArticleDetailsFeatureApi::class,
            object_ = ArticleDetailsFeatureImpl()
        )
    }
}