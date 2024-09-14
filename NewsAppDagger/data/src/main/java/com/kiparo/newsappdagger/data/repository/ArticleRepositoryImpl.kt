/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.data.repository

import com.kiparo.newsappdagger.data.network.api.NewsApi
import com.kiparo.newsappdagger.data.mappers.toDomain
import com.kiparo.newsappdagger.domain.models.Article
import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ArticleRepositoryImpl(
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
