package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.kiparo.lessons.kiparoshopping.presentation.navigation.KiparoShoppingPage
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_16
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_12
import java.util.Locale

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100


@Composable
fun KiparoShoppingTabRow(
    allScreens: List<KiparoShoppingPage>,
    onTabSelected: (KiparoShoppingPage) -> Unit,
    currentScreen: KiparoShoppingPage
) {
    Surface(
        modifier = Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(Modifier.selectableGroup()) {
            allScreens.forEach {
                KiparoTab(
                    text = it.route,
                    icon = it.icon,
                    onSelected = { onTabSelected(it) },
                    isSelected = currentScreen == it
                )
            }
        }
    }
}


@Composable
fun KiparoTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    isSelected: Boolean
) {
    val color = MaterialTheme.colorScheme.onSurface
    val durationMillis = if (isSelected) {
        TabFadeInAnimationDuration
    } else {
        TabFadeOutAnimationDuration
    }
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            delayMillis = TabFadeInAnimationDelay,
            easing = LinearEasing
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (isSelected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec,
        label = ""
    )

    Row(
        modifier = Modifier
            .padding(padding_16)
            .height(TabHeight)
            .animateContentSize()
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = tabTintColor
        )
        if (isSelected) {
            Spacer(modifier = Modifier.width(space_12))
            Text(text = text.uppercase(Locale.getDefault()), color = tabTintColor)
        }
    }
}