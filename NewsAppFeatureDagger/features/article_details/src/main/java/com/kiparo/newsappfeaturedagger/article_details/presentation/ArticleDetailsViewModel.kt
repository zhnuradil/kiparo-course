/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.article_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsArg
import com.kiparo.newsappfeaturedagger.article_details.domain.AddArticleToFavoritesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

internal sealed class Screen {
    data object Nothing : Screen()
    class FullStory(val url: String) : Screen()
    data object Back : Screen()
}

internal class ArticleDetailsViewModel(
    initialArticle: ArticleDetailsArg,
    private val addArticleToFavoritesUseCase: AddArticleToFavoritesUseCase,
) :
    ViewModel() {

    private val _article = MutableLiveData<ArticleDetailsArg>()
    val article: LiveData<ArticleDetailsArg> = _article

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen> = _screen

    init {
        _article.value = initialArticle
    }

    fun fullStoryClicked() {
        article.value?.let {
            _screen.value = Screen.FullStory(it.articleUrl)
            _screen.value = Screen.Nothing
        }
    }

    fun favoriteClick() {
        viewModelScope.launch {
            addArticleToFavoritesUseCase.execute()
        }
    }

    fun backClicked() {
        _screen.value = Screen.Back
        _screen.value = Screen.Nothing
    }
}

class ArticleDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val article: ArticleDetailsArg,
    private val addArticleToFavoritesUseCase: AddArticleToFavoritesUseCase,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return ArticleDetailsViewModel(
            initialArticle = article,
            addArticleToFavoritesUseCase = addArticleToFavoritesUseCase
        ) as T
    }
}

@AssistedFactory
interface ArticleDetailsViewModelFactoryAssisted{
    fun create(article: ArticleDetailsArg): ArticleDetailsViewModelFactory
}