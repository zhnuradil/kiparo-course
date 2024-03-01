/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.data.repository

import com.kiparo.newsapp.data.mappers.toDomain
import com.kiparo.newsapp.data.network.api.NewsApi
import com.kiparo.newsapp.domain.models.ArticleSection
import com.kiparo.newsapp.domain.repository.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ArticleRepositoryImpl(
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher
) : ArticleRepository {
    override suspend fun getGroupedBySection(): List<ArticleSection> = withContext(dispatcher) {
        val sections = newsApi
            .getArticles()
            .groupBy { it.section }
            .toList()
            .map {
                ArticleSection(
                    section = it.first,
                    articles = it.second.map { article -> article.toDomain() })
            }

        sections
    }
}
