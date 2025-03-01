/*
 * Copyright (c) 2023. Kiparo.com
 */

package com.kiparo.customviewapp.presentation.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kiparo.customviewapp.core_ui.theme.KiparoCustomViewAppTheme
import com.kiparo.customviewapp.presentation.features.concert_hall.ConcertHallScreen
import com.kiparo.customviewapp.presentation.features.diagram.DiagramScreen
import com.kiparo.customviewapp.presentation.features.equalizer.EqualizerScreen
import com.kiparo.customviewapp.presentation.features.free_drawing.FreeDrawingScreen
import com.kiparo.customviewapp.presentation.features.main.ui.MainTopBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KiparoCustomViewAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val mainViewModel: MainViewModel = viewModel()
                    val state by mainViewModel.uiState.collectAsStateWithLifecycle()

                    MainScreen(
                        state = state,
                        onConcertHallClick = mainViewModel::onConcertHallClick,
                        onDiagramClick = mainViewModel::onDiagramClick,
                        onEqualizerClick = mainViewModel::onEqualizerClick,
                        onFreeDrawingClick = mainViewModel::onFreeDrawingClick
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainUiState,
    onConcertHallClick: () -> Unit = {},
    onDiagramClick: () -> Unit = {},
    onEqualizerClick: () -> Unit = {},
    onFreeDrawingClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        MainTopBar(
            onConcertHallClick = onConcertHallClick,
            onDiagramClick = onDiagramClick,
            onEqualizerClick = onEqualizerClick,
            onFreeDrawingClick = onFreeDrawingClick
        )

        when (state.screen) {
            MainNav.CONCERT_HALL -> {
                ConcertHallScreen()
            }

            MainNav.DIAGRAM -> {
                DiagramScreen()
            }

            MainNav.EQUALIZER -> {
                EqualizerScreen()
            }

            MainNav.FREE_DRAWING -> {
                FreeDrawingScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MainScreen(state = MainUiState(screen = MainNav.FREE_DRAWING))
}
