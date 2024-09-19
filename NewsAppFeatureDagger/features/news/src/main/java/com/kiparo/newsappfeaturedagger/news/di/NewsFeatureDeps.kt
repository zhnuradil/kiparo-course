package com.kiparo.newsappfeaturedagger.news.di

import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureApi
import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository

interface NewsFeatureDeps {
    val articleRepository: ArticleRepository
    val articleDetailsFeatureApi: ArticleDetailsFeatureApi
    val router: Router
}