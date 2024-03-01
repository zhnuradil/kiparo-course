/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.data.network.api

import com.kiparo.newsapp.data.network.models.ArticleNetwork

interface NewsApi {
    fun getArticles(): List<ArticleNetwork>
}
