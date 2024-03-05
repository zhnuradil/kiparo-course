/*
 * Copyright (c) 2023. Kiparo.ru
 */

package com.kiparo.newsapp.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// For Students, read this:
// https://developer.android.com/jetpack/compose/designsystems/material3#typography

val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = notosansFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = notosansFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = notosansFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal
    )
)

val Typography.myTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = notosansFontFamily,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal
        )
    }
