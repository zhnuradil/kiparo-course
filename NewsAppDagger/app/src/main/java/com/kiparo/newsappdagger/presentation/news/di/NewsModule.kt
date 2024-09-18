package com.kiparo.newsappdagger.presentation.news.di

import com.kiparo.newsappdagger.domain.usecase.GetArticlesUseCase
import com.kiparo.newsappdagger.presentation.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @NewsScope
    @Provides
    fun provideNewsViewModelFactory(getArticlesUseCase: GetArticlesUseCase): NewsViewModelFactory {
        return NewsViewModelFactory(getArticlesUseCase = getArticlesUseCase)
    }

}