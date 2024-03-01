/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.presentation.features.articles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.newsapp.data.network.provideGson
import com.kiparo.newsapp.data.network.provideNewsApi
import com.kiparo.newsapp.data.repository.ArticleRepositoryImpl
import com.kiparo.newsapp.domain.models.ArticleSection
import com.kiparo.newsapp.domain.models.mockArticleSections
import com.kiparo.newsapp.domain.repository.ArticleRepository
import com.kiparo.newsapp.presentation.core_ui.theme.NewsAppTheme
import com.kiparo.newsapp.presentation.features.articles.ui.ArticleTopBar
import com.kiparo.newsapp.presentation.features.articles.ui.Articles
import kotlinx.coroutines.Dispatchers

class ArticlesActivity : ComponentActivity() {

    private val newsRepository: ArticleRepository = ArticleRepositoryImpl(
        newsApi = provideNewsApi(
            apiUrl = com.kiparo.newsapp.BuildConfig.API_URL,
            gson = provideGson()
        ),
        dispatcher = Dispatchers.IO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onSurface
                ) {
                    Column {
                        ArticleTopBar()
                        ArticleScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun ArticleScreen() {
        val articles = remember { mutableStateOf(emptyList<ArticleSection>()) }
        val loading = remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            articles.value = newsRepository.getGroupedBySection()
            loading.value = false
        }

        if (loading.value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Articles(sections = articles.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    NewsAppTheme {
        Column {
            ArticleTopBar()
            Articles(sections = mockArticleSections())
        }
    }
}
