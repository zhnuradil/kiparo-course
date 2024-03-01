/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.newsapp.data.mappers

import com.kiparo.newsapp.data.network.models.ArticleNetwork
import com.kiparo.newsapp.domain.models.Article

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
