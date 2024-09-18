/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.article_details.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kiparo.newsappdagger.core.di.kiparoDi
import com.kiparo.newsappfeaturedagger.article_details.R
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsArg
import com.kiparo.newsappfeaturedagger.article_details.databinding.FragmentArticleDetailsBinding
import com.kiparo.newsappfeaturedagger.article_details.domain.AddArticleToFavoritesUseCase
import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.domain.repository.ArticleRepository

class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    private val viewModel by viewModels<ArticleDetailsViewModel> {
        ArticleDetailsViewModelFactory(
            article = getArticleArg(arguments),
            addArticleToFavoritesUseCase = AddArticleToFavoritesUseCase(
                articleRepository = kiparoDi<ArticleRepository>().value
            )
        )
    }

    private val router = kiparoDi<Router>().value

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArticleDetailsBinding.bind(view)

        binding.backButton.setOnClickListener {
            viewModel.backClicked()
        }

        viewModel.article.observe(viewLifecycleOwner) { article ->
            binding.title.text = article.title
            binding.summary.text = article.summary
            binding.image.setImageURI(article.imageUrl)
            binding.linkButton.setOnClickListener {
                viewModel.fullStoryClicked()
            }
            binding.favoriteButton.setOnClickListener {
                viewModel.favoriteClick()
            }
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            when (it) {
                is Screen.FullStory -> {
                    router.navigateTo(url = it.url)
                }

                is Screen.Back -> {
                    router.back()
                }

                else -> {}
            }
        }
    }

    companion object {

        private const val ARTICLE_DETAILS_EXTRAS = "ARTICLE_DETAILS_EXTRAS"

        @Suppress("DEPRECATION")
        private fun getArticleArg(arguments: Bundle?): ArticleDetailsArg =
            arguments?.getParcelable(ARTICLE_DETAILS_EXTRAS)
                ?: throw IllegalArgumentException("Article could not be null")

        fun getInstance(article: ArticleDetailsArg): Fragment {
            return ArticleDetailsFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(ARTICLE_DETAILS_EXTRAS, article)
                arguments = bundle
            }
        }
    }
}