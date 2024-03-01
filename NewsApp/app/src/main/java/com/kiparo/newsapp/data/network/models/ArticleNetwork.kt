/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleNetwork(
    val title: String?,
    val abstract: String?,
    val section: String,
    var url: String?,
    val multimedia: List<MediaNetwork>?,

    @SerializedName("published_date")
    val publishedDate: Date
)