package com.kiparo.newsappfeaturedagger.di

import com.kiparo.newsappfeaturedagger.data.repository.ArticleRepositoryImpl
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {
    @Binds
    fun bindArticleRepository(repository: ArticleRepositoryImpl): ArticleRepository
}
