package com.kiparo.newsappdagger.presentation.news.di

import com.kiparo.newsappdagger.presentation.news.NewsFragment
import dagger.Subcomponent

@NewsScope
@Subcomponent(modules = [NewsModule::class])
interface NewsComponent {

    fun inject(fragment: NewsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): NewsComponent
    }
}