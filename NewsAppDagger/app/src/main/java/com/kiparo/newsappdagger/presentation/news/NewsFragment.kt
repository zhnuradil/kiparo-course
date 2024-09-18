/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappdagger.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiparo.newsappdagger.R
import com.kiparo.newsappdagger.core.ui.recycler.SpaceDecoration
import com.kiparo.newsappdagger.databinding.FragmentNewsBinding
import com.kiparo.newsappdagger.di.DiProvider
import com.kiparo.newsappdagger.presentation.article_details.ArticleDetailsArg
import com.kiparo.newsappdagger.presentation.article_details.ArticleDetailsFragment
import com.kiparo.newsappdagger.presentation.navigation.Navigator
import dagger.Lazy
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var vmFactory: Lazy<NewsViewModelFactory>

    private val viewModel by viewModels<NewsViewModel> { vmFactory.get() }
    private val adapter = NewsAdapter {
        viewModel.articleClicked(article = it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsComponent = DiProvider.appComponent.newsComponent
        newsComponent.build().inject(this)

        if(savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view)

        binding.toolbar.inflateMenu(R.menu.menu_news)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    true
                }

                else -> false
            }
        }

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.newsRecyclerView.addItemDecoration(
            SpaceDecoration(
                spaceSize = resources.getDimensionPixelSize(
                    com.kiparo.newsappdagger.core.ui.R.dimen.padding_10
                )
            )
        )
        binding.newsRecyclerView.adapter = adapter

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.items = it
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            if (it is NewsScreen.Details)
                showArticleDetails(articleDetails = it.article)
        }
    }

    private fun showArticleDetails(articleDetails: ArticleDetailsArg) {
        (requireActivity() as Navigator).navigateTo(
            fragment = ArticleDetailsFragment.getInstance(
                articleDetails
            ), addToBackStack = true
        )
    }
}