/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.domain.usecase

import com.kiparo.newsappdagger.domain.models.Article
import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {

    suspend fun execute(): List<Article> {
        return articleRepository.get()
    }
}