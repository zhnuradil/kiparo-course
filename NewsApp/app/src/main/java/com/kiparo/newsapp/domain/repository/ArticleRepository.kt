/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.domain.repository

import com.kiparo.newsapp.domain.models.ArticleSection

interface ArticleRepository {
    suspend fun getGroupedBySection(): List<ArticleSection>
}
