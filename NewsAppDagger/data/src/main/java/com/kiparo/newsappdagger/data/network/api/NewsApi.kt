/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.data.network.api

import com.kiparo.newsappdagger.data.network.model.ArticleNetwork

interface NewsApi {
    fun getArticles(): List<ArticleNetwork>
}
