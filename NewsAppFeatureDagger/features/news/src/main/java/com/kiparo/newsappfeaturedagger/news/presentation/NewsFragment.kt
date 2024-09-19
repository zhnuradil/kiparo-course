/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.news.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsArg
import com.kiparo.newsappfeaturedagger.article_details.api.ArticleDetailsFeatureApi
import com.kiparo.newsappfeaturedagger.core.navigation.Router
import com.kiparo.newsappfeaturedagger.core.ui.recycler.SpaceDecoration
import com.kiparo.newsappfeaturedagger.news.R
import com.kiparo.newsappfeaturedagger.news.databinding.FragmentNewsBinding
import com.kiparo.newsappfeaturedagger.news.di.DaggerNewsComponent
import com.kiparo.newsappfeaturedagger.news.di.NewsFeatureDepsProvider
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var vmFactory: NewsViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var articleDetailsFeatureApi: ArticleDetailsFeatureApi

    private val viewModel by viewModels<NewsViewModel> { vmFactory }

    private val adapter = NewsAdapter {
        viewModel.articleClicked(article = it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsComponent = DaggerNewsComponent
            .builder()
            .addDeps(NewsFeatureDepsProvider.deps)
            .build()

        newsComponent.inject(this)

        if (savedInstanceState == null) {
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
                    com.kiparo.newsappfeaturedagger.core.ui.R.dimen.padding_10
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
        router.navigateTo(
            fragment = articleDetailsFeatureApi.open(articleDetails),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): NewsFragment = NewsFragment()
    }
}