/*
 * Copyright (c) 2023. Kiparo.com
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.kiparo.customviewapp.presentation.features.main.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kiparo.customviewapp.R

private const val OFFSET_X = 10
private const val OFFSET_Y = (-60)

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    onConcertHallClick: () -> Unit = {},
    onDiagramClick: () -> Unit = {},
    onEqualizerClick: () -> Unit = {},
    onFreeDrawingClick: () -> Unit = {}
) {

    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = {
                dropDownMenuExpanded = true
            }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Options")
            }

            DropdownMenu(
                expanded = dropDownMenuExpanded,
                onDismissRequest = {
                    dropDownMenuExpanded = false
                },
                offset = DpOffset(x = OFFSET_X.dp, y = OFFSET_Y.dp)
            ) {
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onConcertHallClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.concert_hall))
                })
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onDiagramClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.diagram))
                })
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onEqualizerClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.equalizer))
                })
                DropdownMenuItem(onClick = {
                    dropDownMenuExpanded = false
                    onFreeDrawingClick.invoke()
                }, text = {
                    Text(text = stringResource(id = R.string.free_drawing))
                })
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    MainTopBar()
}