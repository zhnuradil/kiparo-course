package com.kiparo.customviewapp.presentation.features.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

enum class MainNav {
    CONCERT_HALL,
    DIAGRAM,
    EQUALIZER,
    FREE_DRAWING
}

data class MainUiState(
    val screen: MainNav,
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MainUiState(
            screen = MainNav.FREE_DRAWING,
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState

    fun onConcertHallClick() {
        _uiState.update {
            it.copy(
                screen = MainNav.CONCERT_HALL
            )
        }
    }

    fun onDiagramClick() {
        _uiState.update {
            it.copy(
                screen = MainNav.DIAGRAM
            )
        }
    }

    fun onEqualizerClick() {
        _uiState.update {
            it.copy(
                screen = MainNav.EQUALIZER
            )
        }
    }

    fun onFreeDrawingClick() {
        _uiState.update {
            it.copy(
                screen = MainNav.FREE_DRAWING
            )
        }
    }
}