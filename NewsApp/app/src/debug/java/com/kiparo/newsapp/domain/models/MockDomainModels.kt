/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.domain.models

import java.util.Date
import kotlin.random.Random

fun mockArticle(
    title: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    description: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    section: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    articleUrl: String = "",
    imageUrl: String = "",
    publishedAt: Date = Date(),
) = Article(
    title = title,
    description = description,
    section = section,
    articleUrl = articleUrl,
    imageUrl = imageUrl,
    publishedAt = publishedAt
)

fun mockArticleSection(
    section: String = Random.nextLong(1, 9999_9999_9999_9999).toString(),
    articles: List<Article> = mockArticles(),
) = ArticleSection(
    section = section,
    articles = articles,
)

fun mockArticles(): List<Article> {
    return listOf(
        mockArticle(),
        mockArticle(),
        mockArticle(),
        mockArticle(),
        mockArticle(),
    )
}

internal fun mockArticleSections(): List<ArticleSection> {
    return listOf(
        mockArticleSection(),
        mockArticleSection(),
        mockArticleSection()
    )
}