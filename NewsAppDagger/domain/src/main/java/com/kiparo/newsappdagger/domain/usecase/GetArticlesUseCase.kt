/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.domain.usecase

import com.kiparo.newsappdagger.domain.models.Article
import com.kiparo.newsappdagger.domain.repository.ArticleRepository

class GetArticlesUseCase(private val articleRepository: ArticleRepository) {

    suspend fun execute(): List<Article> {
        return articleRepository.get()
    }
}