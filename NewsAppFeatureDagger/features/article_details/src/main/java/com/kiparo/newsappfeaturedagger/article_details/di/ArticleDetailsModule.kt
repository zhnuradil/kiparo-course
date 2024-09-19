package com.kiparo.newsappfeaturedagger.article_details.di

import com.kiparo.newsappfeaturedagger.article_details.domain.AddArticleToFavoritesUseCase
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides

@Module
class ArticleDetailsModule {

    @Provides
    fun providerAddArticleToFavoritesUseCase(articleRepository: ArticleRepository): AddArticleToFavoritesUseCase {
        return AddArticleToFavoritesUseCase(articleRepository = articleRepository)
    }
}