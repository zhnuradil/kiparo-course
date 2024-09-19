package com.kiparo.newsappfeaturedagger.article_details.di

import com.kiparo.newsappfeaturedagger.article_details.presentation.ArticleDetailsFragment
import dagger.Component

@Component(
    modules = [ArticleDetailsModule::class],
    dependencies = [ArticleDetailsFeatureDeps::class]
)
@ArticleDetailsScope
interface ArticleDetailsComponent {

    fun inject(fragment: ArticleDetailsFragment)

    @Component.Builder
    interface Builder {
        fun addDeps(deps: ArticleDetailsFeatureDeps): Builder
        fun build(): ArticleDetailsComponent
    }
}