package com.kiparo.wildcatalog.presentation.catalog.tools

import androidx.recyclerview.widget.RecyclerView
import com.kiparo.wildcatalog.presentation.catalog.delegates.CatalogItemDelegateAdapter
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun List<CatalogItemDelegateAdapter<out CatalogItem, out RecyclerView.ViewHolder>>.findAdapterIndexFor(
    item: CatalogItem
): Int? =
    indexOfFirst { adapter ->
        adapter.itemClass == item.javaClass
    }.takeIf {
        it >= 0
    }


fun CoroutineScope.letsDoWithTimer(time: Long, callback: () -> Unit): Job = launch {
    while (isActive) {
        callback()
        delay(time)
    }
}

fun nextFloat(minF: Float, maxF: Float): Float = minF + Random.nextFloat() * (maxF - minF)

