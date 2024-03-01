/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kiparo.newsapp.data.network.api.NewsApi
import com.kiparo.newsapp.data.network.api.NewsApiNetwork

internal fun provideGson(): Gson {
    return GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        .create()
}

internal fun provideNewsApi(apiUrl: String, gson: Gson): NewsApi {
    return NewsApiNetwork(
        apiUrl = apiUrl,
        gson = gson
    )
}