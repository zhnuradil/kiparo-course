/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappfeaturedagger.article_details.domain

import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository

class AddArticleToFavoritesUseCase(private val articleRepository: ArticleRepository) {

    suspend fun execute() {
        articleRepository.addToFavorites()
    }
}