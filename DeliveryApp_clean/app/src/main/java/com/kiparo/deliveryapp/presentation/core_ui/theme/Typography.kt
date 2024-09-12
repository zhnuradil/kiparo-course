/*
 * Copyright (c) 2023. Kiparo academy
 */

package com.kiparo.deliveryapp.presentation.core_ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// For Students, read this:
// https://developer.android.com/jetpack/compose/designsystems/material3#typography

val typography = Typography(
    labelSmall = TextStyle(
        fontFamily = montseratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = montseratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.4.sp
    ),
    bodySmall = TextStyle(
        fontFamily = montseratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    titleSmall = TextStyle(
        fontFamily = montseratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
)

// Example of custom Typography
val Typography.bodyMediumBold: TextStyle
    @Composable
    get() =
        TextStyle(
            fontFamily = montseratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp
        )
