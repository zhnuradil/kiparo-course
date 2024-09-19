package com.kiparo.newsappfeaturedagger.article_details.di

import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository

interface ArticleDetailsFeatureDeps {
    val articleRepository: ArticleRepository
    val router: Router
}