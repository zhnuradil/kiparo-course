package com.kiparo.newsappdagger.di

import com.kiparo.newsappdagger.data.repository.ArticleRepositoryImpl
import com.kiparo.newsappdagger.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {

    @Binds
    fun bindArticleRepository(repository: ArticleRepositoryImpl): ArticleRepository
}