/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.presentation.features.articles.ui

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiparo.newsapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.onSurface),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}

@Composable
@Preview
private fun Preview() {
    ArticleTopBar()
}