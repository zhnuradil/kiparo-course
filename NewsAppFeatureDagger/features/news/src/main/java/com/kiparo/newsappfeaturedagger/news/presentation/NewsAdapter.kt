/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsappfeaturedagger.news.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kiparo.newsappfeaturedagger.news.databinding.ItemNewsBinding
import com.kiparo.newsappfeaturedagger.domain.models.Article

internal class NewsAdapter(
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.Holder>() {

    var items: List<Article> = emptyList()
        set(newList) {
            val diffUtilCallback = DiffCallback(old = field, new = newList)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = newList
            diffUtilResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            binding = ItemNewsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(news = items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: Article) {
            binding.title.text = news.title
            binding.root.setOnClickListener { onItemClick.invoke(news) }
            binding.image.setImageURI(news.imageUrl)
        }
    }

    inner class DiffCallback(
        private val old: List<Article>,
        private val new: List<Article>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].articleUrl == new[newItemPosition].articleUrl
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }
    }
}