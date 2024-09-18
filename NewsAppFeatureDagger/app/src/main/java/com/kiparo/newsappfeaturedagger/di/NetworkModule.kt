package com.kiparo.newsappfeaturedagger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiparo.newsappfeaturedagger.BuildConfig
import com.kiparo.newsappfeaturedagger.data.network.api.NewsApi
import com.kiparo.newsappfeaturedagger.data.network.api.NewsApiNetwork
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Qualifier

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
            .create()
    }

    @Provides
    fun provideNewsApi(
        @ApiUrl apiUrl: String,
        @ApiPath newsPath: String,
        gson: Gson,
    ): NewsApi {
        return NewsApiNetwork(
            apiUrl = apiUrl + newsPath,
            gson = gson
        )
    }

    @Provides
    @ApiUrl
    fun provideBaseApiUrl(): String {
        return BuildConfig.KIPARO_BASE_API_URL
    }

    @Provides
    @ApiPath
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

