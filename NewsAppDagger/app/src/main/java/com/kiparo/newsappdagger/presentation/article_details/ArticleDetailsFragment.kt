/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.article_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kiparo.newsappdagger.R
import com.kiparo.newsappdagger.databinding.FragmentArticleDetailsBinding
import com.kiparo.newsappdagger.di.kiparoDi
import com.kiparo.newsappdagger.domain.usecase.AddArticleToFavoritesUseCase
import com.kiparo.newsappdagger.presentation.navigation.Navigator

@Suppress("DEPRECATION")
class ArticleDetailsFragment : Fragment(R.layout.fragment_article_details) {

    private val viewModel by viewModels<ArticleDetailsViewModel> {
        ArticleDetailsViewModelFactory(
            article = getArticleArg(arguments),
            addArticleToFavoritesUseCase = kiparoDi<AddArticleToFavoritesUseCase>().value
        )
    }

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
                    (requireActivity() as Navigator).navigateTo(url = it.url)
                }

                is Screen.Back -> {
                    (requireActivity() as Navigator).back()
                }

                else -> {}
            }
        }
    }

    companion object {

        private const val ARTICLE_DETAILS_EXTRAS = "ARTICLE_DETAILS_EXTRAS"

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