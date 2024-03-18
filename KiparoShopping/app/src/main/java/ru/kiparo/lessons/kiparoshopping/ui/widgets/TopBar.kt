package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_0
import ru.kiparo.lessons.kiparoshopping.ui.theme.padding_16

@ExperimentalMaterial3Api
@Composable
fun TopBar(title:String, action: @Composable () -> Unit = {}){
    CenterAlignedTopAppBar(
        windowInsets = WindowInsets(
            left = padding_16,
            right = padding_16,
            top = padding_0,
            bottom = padding_0),
        title = {
            Text(
                title,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.inverseSurface,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_left_arrow),
                    contentDescription = "Navigate back"
                )
            }
        },
        actions = { action() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarPreview() {
    KiparoShoppingTheme {
        TopBar(
            title = "Top bar title",
            action = {}
        )
    }
}