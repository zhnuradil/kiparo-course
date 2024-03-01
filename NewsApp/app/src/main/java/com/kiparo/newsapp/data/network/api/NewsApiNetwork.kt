/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.data.network.api

import com.google.gson.Gson
import com.kiparo.newsapp.data.network.models.ArticleNetwork
import com.kiparo.newsapp.data.network.models.NewsNetwork
import java.net.HttpURLConnection
import java.net.URL

class NewsApiNetwork(
    private val apiUrl: String,
    private val gson: Gson,
) : NewsApi {
    override fun getArticles(): List<ArticleNetwork> {
        val url = URL(apiUrl)
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val news = gson.fromJson(json, NewsNetwork::class.java)

        return news.results
    }
}
