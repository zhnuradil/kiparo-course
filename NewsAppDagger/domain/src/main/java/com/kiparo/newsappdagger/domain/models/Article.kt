/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.newsappdagger.domain.models

import java.util.Date

data class Article(
    val title: String,
    val description: String,
    val section: String,
    var articleUrl: String,
    val imageUrl: String,
    val publishedAt: Date,
)