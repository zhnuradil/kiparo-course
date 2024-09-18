package com.kiparo.newsappdagger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiparo.newsappdagger.BuildConfig
import com.kiparo.newsappdagger.data.network.api.NewsApi
import com.kiparo.newsappdagger.data.network.api.NewsApiNetwork
import com.kiparo.newsappdagger.data.repository.ArticleRepositoryImpl
import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import com.kiparo.newsappdagger.domain.usecase.AddArticleToFavoritesUseCase
import com.kiparo.newsappdagger.domain.usecase.GetArticlesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(subcomponents = [])
class AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
            .create()
    }

    @Reusable
    @Provides
    fun provideNewsApi(
        @ApiUrl baseApiUrl: String,
        @ApiPath newsApiPath: String,
        gson: Gson
    ): NewsApi {
        return NewsApiNetwork(
            apiUrl = baseApiUrl + newsApiPath,
            gson = gson
        )
    }

    @ApiUrl
    @Provides
    fun provideBaseApiUrl(): String {
        return BuildConfig.KIPARO_BASE_API_URL
    }

    @ApiPath
    @Provides
    fun provideNewsApiPath(): String {
        return BuildConfig.KIPARO_NEWS_API_PATH
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiPath