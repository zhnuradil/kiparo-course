/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.newsappfeaturedagger.data.mappers

import com.kiparo.newsappfeaturedagger.data.network.model.ArticleNetwork
import com.kiparo.newsappfeaturedagger.domain.models.Article

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
