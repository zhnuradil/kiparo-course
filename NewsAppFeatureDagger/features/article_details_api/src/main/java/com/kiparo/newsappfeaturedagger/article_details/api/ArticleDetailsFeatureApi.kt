package com.kiparo.newsappfeaturedagger.article_details.api

import androidx.fragment.app.Fragment

interface ArticleDetailsFeatureApi {
    fun open(article: ArticleDetailsArg): Fragment
}