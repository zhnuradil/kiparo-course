/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.data.network.api

import com.kiparo.newsappfeaturedagger.data.network.model.ArticleNetwork

interface NewsApi {
    fun getArticles(): List<ArticleNetwork>
}
