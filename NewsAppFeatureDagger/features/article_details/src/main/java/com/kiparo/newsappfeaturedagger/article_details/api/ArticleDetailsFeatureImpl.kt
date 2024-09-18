package com.kiparo.newsappfeaturedagger.article_details.api

import androidx.fragment.app.Fragment
import com.kiparo.newsappfeaturedagger.article_details.presentation.ArticleDetailsFragment
import javax.inject.Inject

class ArticleDetailsFeatureImpl @Inject constructor() : ArticleDetailsFeatureApi {
    override fun open(article: ArticleDetailsArg): Fragment =
        ArticleDetailsFragment.getInstance(article)
}