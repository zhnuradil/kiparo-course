/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kiparo.newsappdagger.domain.models.Article
import com.kiparo.newsappdagger.domain.usecase.GetArticlesUseCase
import com.kiparo.newsappdagger.presentation.article_details.ArticleDetailsArg
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed class NewsScreen {
    data object Nothing : NewsScreen()
    class Details(val article: ArticleDetailsArg) : NewsScreen()
}

internal class NewsViewModel(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {

    private val _news = MutableLiveData<List<Article>>(emptyList())
    val news: LiveData<List<Article>> = _news

    private val _screen = MutableLiveData<NewsScreen>()
    val screen: LiveData<NewsScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val articles = getArticlesUseCase.execute()
            _news.postValue(articles)
        }
    }

    fun articleClicked(article: Article) {
        val articleDetails = ArticleDetailsArg(
            title = article.title,
            summary = article.description,
            imageUrl = article.imageUrl,
            articleUrl = article.articleUrl
        )
        _screen.value = NewsScreen.Details(article = articleDetails)
        _screen.value = NewsScreen.Nothing
    }
}

class NewsViewModelFactory (private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return NewsViewModel(
            getArticlesUseCase = getArticlesUseCase
        ) as T
    }
}
