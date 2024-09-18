/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.domain.usecase

import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import javax.inject.Inject

class AddArticleToFavoritesUseCase @Inject constructor(private val articleRepository: ArticleRepository) {

    suspend fun execute() {
        articleRepository.addToFavorites()
    }
}