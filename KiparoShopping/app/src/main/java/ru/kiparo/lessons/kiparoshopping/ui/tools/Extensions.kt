/*
 * Copyright (c) 2023. Kiparo.ru
 */

package ru.kiparo.lessons.kiparoshopping.ui.tools

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key

@OptIn(ExperimentalComposeUiApi::class)
fun FocusManager.onKeyEvent(key: KeyEvent, index: Int, limit: Int): Boolean {
    return when {
        key.key.keyCode in Key.Zero.keyCode..Key.Nine.keyCode -> {
            if (index == limit-1) clearFocus(true)
            else moveFocus(FocusDirection.Next)
            true
        }
        key.key == Key.Backspace -> {
            moveFocus(FocusDirection.Previous)
            true
        }
        else -> {
            true
        }
    }
}

fun String.toListOfSingleDigits() = map {
    "$it".toIntOrNull()
}.filterNotNull()