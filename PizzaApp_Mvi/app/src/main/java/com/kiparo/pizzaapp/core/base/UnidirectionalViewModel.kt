package com.kiparo.pizzaapp.core.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow


interface UnidirectionalViewModel<STATE, ACTION, EVENT> {
    val uiState: StateFlow<STATE>

    val event: StateFlow<EVENT>

    fun onAction(action: ACTION)

    fun consume()
}

@Composable
fun <STATE, ACTION, EVENT> UnidirectionalViewModel<STATE, ACTION, EVENT>.useEvent(
    function: (EVENT) -> Unit
) {
    val key by event.collectAsStateWithLifecycle()

    DisposableEffect(key) {
        function(key)
        onDispose { consume() }
    }
}
