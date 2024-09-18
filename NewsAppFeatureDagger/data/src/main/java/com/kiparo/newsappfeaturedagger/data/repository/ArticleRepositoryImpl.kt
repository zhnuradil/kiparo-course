/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.data.repository

import com.kiparo.newsappfeaturedagger.data.network.api.NewsApi
import com.kiparo.newsappfeaturedagger.data.mappers.toDomain
import com.kiparo.newsappfeaturedagger.domain.models.Article
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher,
) : ArticleRepository {
    override suspend fun get(): List<Article> = withContext(dispatcher) {
        newsApi
            .getArticles()
            .map {
                it.toDomain()
            }
    }

    override suspend fun addToFavorites() {
        // Some code to add article to favorites
    }
}
