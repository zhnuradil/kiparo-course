/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.presentation.features.articles.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kiparo.newsapp.core_ui.theme.padding_16
import com.kiparo.newsapp.core_ui.theme.space_16
import com.kiparo.newsapp.domain.models.ArticleSection

// For Students, read this:
// https://developer.android.com/jetpack/compose/lists

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Articles(sections: List<ArticleSection>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface),
        verticalArrangement = Arrangement.spacedBy(space_16),
        contentPadding = PaddingValues(padding_16)
    ) {
        sections.forEach { section ->
            item {
                ArticleSectionTitle(title = section.section)
            }
            items(section.articles) { article ->
                ArticleItem(article = article)
            }
        }
    }
}