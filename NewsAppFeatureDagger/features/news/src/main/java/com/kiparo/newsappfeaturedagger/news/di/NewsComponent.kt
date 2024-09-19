package com.kiparo.newsappfeaturedagger.news.di

import com.kiparo.newsappfeaturedagger.news.presentation.NewsFragment
import dagger.Component

@Component(modules = [NewsModule::class], dependencies = [NewsFeatureDeps::class])
@NewsScope
interface NewsComponent {

    fun inject(fragment: NewsFragment)

    @Component.Builder
    interface Builder {
        fun addDeps(deps: NewsFeatureDeps): Builder
        fun build(): NewsComponent
    }
}