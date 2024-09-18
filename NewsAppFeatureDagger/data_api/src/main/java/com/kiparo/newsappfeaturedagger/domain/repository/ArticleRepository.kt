/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.domain.repository

import com.kiparo.newsappfeaturedagger.domain.models.Article

interface ArticleRepository {
    suspend fun get(): List<Article>
    suspend fun addToFavorites()
}
