/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappfeaturedagger.news.domain

import com.kiparo.newsappfeaturedagger.domain.models.Article
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository

class GetArticlesUseCase(private val articleRepository: ArticleRepository) {

    suspend fun execute(): List<Article> {
        return articleRepository.get()
    }
}