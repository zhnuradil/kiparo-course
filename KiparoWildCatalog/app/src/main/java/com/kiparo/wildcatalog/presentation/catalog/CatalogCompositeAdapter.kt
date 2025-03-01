package com.kiparo.wildcatalog.presentation.catalog

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kiparo.wildcatalog.presentation.catalog.delegates.CatalogItemDelegateAdapter
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import com.kiparo.wildcatalog.presentation.catalog.tools.findAdapterIndexFor

class CatalogCompositeAdapter private constructor(
    private val delegateAdapters: List<CatalogItemDelegateAdapter<CatalogItem, RecyclerView.ViewHolder>>
) : ListAdapter<CatalogItem, RecyclerView.ViewHolder>(CatalogDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        delegateIndex: Int
    ): RecyclerView.ViewHolder {
        return delegateAdapters[delegateIndex].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters[getItemViewType(position)].bindViewHolder(getItem(position), holder)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val catalogItemPayloads = payloads.map { it as CatalogItem.PayloadChange }
        delegateAdapters[getItemViewType(position)].bindViewHolder(
            getItem(position),
            holder,
            catalogItemPayloads
        )
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return delegateAdapters.findAdapterIndexFor(item)
            ?: throw Exception("Not supported item: $${item::class}")
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        delegateAdapters.forEach { adapter -> adapter.onAttachedToRecyclerView(recyclerView) }
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        delegateAdapters.forEach { adapter -> adapter.onDetachedFromRecyclerView(recyclerView) }
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegateAdapters[holder.itemViewType].onViewAttachedToWindow(holder)
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegateAdapters[holder.itemViewType].onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegateAdapters[holder.itemViewType].onViewRecycled(holder)
        super.onViewRecycled(holder)
    }


    private class CatalogDiffUtil : DiffUtil.ItemCallback<CatalogItem>() {

        override fun areItemsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
            return oldItem.id() == newItem.id()
        }

        override fun areContentsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
            return oldItem.content(newItem)
        }

        override fun getChangePayload(oldItem: CatalogItem, newItem: CatalogItem): Any? {
            return oldItem.getPayload(newItem)
        }
    }

    class Builder {
        private val delegateAdapters: MutableList<CatalogItemDelegateAdapter<CatalogItem, RecyclerView.ViewHolder>> =
            mutableListOf()

        fun add(delegateAdapter: CatalogItemDelegateAdapter<out CatalogItem, out RecyclerView.ViewHolder>): Builder {
            delegateAdapters.add(delegateAdapter as CatalogItemDelegateAdapter<CatalogItem, RecyclerView.ViewHolder>)
            return this
        }

        fun build(): CatalogCompositeAdapter = CatalogCompositeAdapter(delegateAdapters)
    }
}