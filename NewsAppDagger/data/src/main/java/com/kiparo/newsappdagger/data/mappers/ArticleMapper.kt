/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.newsappdagger.data.mappers

import com.kiparo.newsappdagger.data.network.model.ArticleNetwork
import com.kiparo.newsappdagger.domain.models.Article

fun ArticleNetwork.toDomain(): Article {
    return Article(
        title = title ?: "",
        description = abstract ?: "",
        section = section,
        articleUrl = url ?: "",
        imageUrl = multimedia?.first()?.url ?: "",
        publishedAt = publishedDate
    )
}
