/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.kiparo.newsappdagger.di.DiProvider
import com.kiparo.newsappdagger.di.GlobalDIImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiparo.newsappdagger.data.network.api.NewsApi
import com.kiparo.newsappdagger.BuildConfig
import com.kiparo.newsappdagger.data.network.api.NewsApiNetwork
import com.kiparo.newsappdagger.data.repository.ArticleRepositoryImpl
import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import com.kiparo.newsappdagger.domain.usecase.AddArticleToFavoritesUseCase
import com.kiparo.newsappdagger.domain.usecase.GetArticlesUseCase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        setupDI()
    }

    private fun setupDI() {
        DiProvider.di = GlobalDIImpl()

        DiProvider.di.add(
            key = CoroutineDispatcher::class,
            object_ = Dispatchers.IO
        )

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

        DiProvider.di.add(
            key = GetArticlesUseCase::class,
            object_ = GetArticlesUseCase(
                articleRepository = DiProvider.di.get(ArticleRepository::class),
            )
        )

        DiProvider.di.add(
            key = AddArticleToFavoritesUseCase::class,
            object_ = AddArticleToFavoritesUseCase(
                articleRepository = DiProvider.di.get(ArticleRepository::class),
            )
        )
    }
}