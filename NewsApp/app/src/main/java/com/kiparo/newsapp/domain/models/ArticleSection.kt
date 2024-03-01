package com.kiparo.newsapp.domain.models

data class ArticleSection(
    val section: String,
    val articles: List<Article>
)