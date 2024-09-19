package com.kiparo.newsappfeaturedagger.di

import android.content.Context
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureApi
import com.kiparo.newsappfeaturedagger.article_details.di.ArticleDetailsFeatureDeps
import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository
import com.kiparo.newsappfeaturedagger.news.di.NewsFeatureDeps
import com.kiparo.newsappfeaturedagger.presentation.navigation.NavigatorFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoriesModule::class,
        FeaturesModule::class]
)
interface AppComponent: NewsFeatureDeps, ArticleDetailsFeatureDeps {

    override val router: Router
    override val articleRepository: ArticleRepository
    override val articleDetailsFeatureApi: ArticleDetailsFeatureApi

    fun inject(fragment: NavigatorFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}