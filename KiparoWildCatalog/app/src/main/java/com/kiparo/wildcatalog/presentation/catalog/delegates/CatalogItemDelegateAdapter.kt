package com.kiparo.wildcatalog.presentation.catalog.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem

interface CatalogItemDelegateAdapter<I, VH> {
    val itemClass: Class<out I>

    fun createViewHolder(parent: ViewGroup): VH
    fun bindViewHolder(item: I, viewHolder: VH)
    fun bindViewHolder(item: I, viewHolder: VH, payloads: List<CatalogItem.PayloadChange>)
    fun onAttachedToRecyclerView(recyclerView: RecyclerView) = Unit
    fun onDetachedFromRecyclerView(recyclerView: RecyclerView) = Unit
    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = Unit
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = Unit
    fun onViewRecycled(holder: RecyclerView.ViewHolder) = Unit
}