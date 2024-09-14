/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.domain.repository

import com.kiparo.newsappdagger.domain.models.Article

interface ArticleRepository {
    suspend fun get(): List<Article>
    suspend fun addToFavorites()
}
