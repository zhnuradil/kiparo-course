/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.article_details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDetailsArg(
    val title: String,
    val summary: String,
    val imageUrl: String,
    val articleUrl: String
) : Parcelable