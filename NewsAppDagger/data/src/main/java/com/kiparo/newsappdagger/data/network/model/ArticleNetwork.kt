/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.data.network.model

import com.google.gson.annotations.SerializedName
import com.kiparo.newsappdagger.data.network.model.MediaNetwork
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