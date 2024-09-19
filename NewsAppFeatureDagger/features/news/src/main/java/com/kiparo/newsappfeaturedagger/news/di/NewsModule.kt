package com.kiparo.newsappfeaturedagger.news.di

import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import com.kiparo.newsappfeaturedagger.news.domain.GetArticlesUseCase
import com.kiparo.newsappfeaturedagger.news.presentation.NewsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @Provides
    fun providerNewsViewModelFactory(getArticlesUseCase: GetArticlesUseCase): NewsViewModelFactory {
        return NewsViewModelFactory(getArticlesUseCase = getArticlesUseCase)
    }

    @Provides
    fun providerGetArticlesUseCase(articleRepository: ArticleRepository): GetArticlesUseCase {
        return GetArticlesUseCase(articleRepository = articleRepository)
    }
}