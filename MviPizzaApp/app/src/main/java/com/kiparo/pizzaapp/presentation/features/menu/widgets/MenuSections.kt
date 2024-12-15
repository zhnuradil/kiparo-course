package com.kiparo.pizzaapp.presentation.features.menu.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.kiparo.pizzaapp.core.design.theme.padding16
import com.kiparo.pizzaapp.core.design.theme.space8
import com.kiparo.pizzaapp.core.ui.animateScrollToFullyVisibleItem
import com.kiparo.pizzaapp.domain.models.MenuSection
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
fun MenuSections(
    modifier: Modifier = Modifier,
    sections: ImmutableList<MenuSection>,
    selected: Int,
    onSectionSelect: (String) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    //TODO: check the recomposition impact
    SideEffect {
        coroutineScope.launch {
            listState.animateScrollToFullyVisibleItem(index = selected)
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(space8),
        contentPadding = PaddingValues(start = padding16, end = padding16)
    ) {
        itemsIndexed(sections) { index, section ->
            MenuCategorySection(
                id = section.section,
                title = section.section,
                imageUrl = section.image,
                contentDescription = section.section,
                selected = index == selected,
                onClick = { id ->
                    onSectionSelect(id)
                })
        }
    }
}