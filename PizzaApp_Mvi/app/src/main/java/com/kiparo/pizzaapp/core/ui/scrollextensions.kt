package com.kiparo.pizzaapp.core.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable

// This function scrolls to the item in the list in such a way that
// the item is fully visible + content padding
suspend fun LazyListState.animateScrollToFullyVisibleItem(index: Int) {
    with(layoutInfo) {
        val itemVisibleInfo = visibleItemsInfo.find { it.index == index }
        val itemSize = visibleItemsInfo.first().size
        // if selected item is half visible from the right
        if ((itemVisibleInfo == null
                    || ((viewportEndOffset - itemVisibleInfo.offset) < itemVisibleInfo.size))) {
            val itemScrollOffset = (viewportEndOffset - itemSize) - afterContentPadding
            animateScrollToItem(index, -itemScrollOffset)
        }
        // if selected item is half visible from the left
        else if(itemVisibleInfo.offset < 0){
            animateScrollToItem(index, 0)
        }
    }
}

fun LazyGridScope.spanned(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}