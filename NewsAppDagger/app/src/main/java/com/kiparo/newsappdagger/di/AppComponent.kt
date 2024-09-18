package com.kiparo.newsappdagger.di

import android.content.Context
import com.kiparo.newsappdagger.presentation.article_details.ArticleDetailsFragment
import com.kiparo.newsappdagger.presentation.news.di.NewsComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoriesModule::class, SubModule::class])
interface AppComponent {

    val newsComponent: NewsComponent.Builder

    fun inject(fragment: ArticleDetailsFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun addContext(context: Context): Builder

        fun build(): AppComponent
    }
}