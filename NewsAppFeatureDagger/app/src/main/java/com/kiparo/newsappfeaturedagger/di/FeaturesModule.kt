package com.kiparo.newsappfeaturedagger.di

import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureApi
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureImpl
import com.kiparo.newsappfeaturedagger.news.api.NewsFeatureApi
import com.kiparo.newsappfeaturedagger.news.api.NewsFeatureImpl
import dagger.Binds
import dagger.Module

@Module
interface FeaturesModule {
    @Binds
    fun bindNewsFeature(featureApi: NewsFeatureImpl): NewsFeatureApi

    @Binds
    fun bindArticleDetailsFeature(featureApi: ArticleDetailsFeatureImpl): ArticleDetailsFeatureApi
}
