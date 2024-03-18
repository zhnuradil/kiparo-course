package ru.kiparo.lessons.kiparoshopping.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.kiparo.lessons.kiparoshopping.R

val creditCardFamily = FontFamily(
    Font(R.font.credit_card, FontWeight.Normal)
)

val ptFamily = FontFamily(
    Font(R.font.pt_mono, FontWeight.Normal)
)

val workSansFamily = FontFamily(
    Font(R.font.worksans_regular, FontWeight.Normal)
)

val playfairFamily = FontFamily(
    Font(R.font.playfairdisplay_black, FontWeight.Black),
    Font(
        resId = R.font.playfairdisplay_blackitalic,
        style = FontStyle.Italic,
        weight = FontWeight.Black
    ),
    Font(resId = R.font.playfairdisplay_bold,
        style = FontStyle.Normal,
        weight = FontWeight.Bold),
    Font(
        resId = R.font.playfairdisplay_bolditalic,
        style = FontStyle.Italic,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.playfairdisplay_italic,
        style = FontStyle.Italic,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.playfairdisplay_medium,
        style = FontStyle.Normal,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.playfairdisplay_mediumitalic,
        style = FontStyle.Italic,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.playfairdisplay_regular,
        style = FontStyle.Normal,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.playfairdisplay_semibold,
        style = FontStyle.Normal,
        weight = FontWeight.W300
    ),
    Font(
        resId = R.font.playfairdisplay_semibolditalic,
        style = FontStyle.Italic,
        weight = FontWeight.W300
    ),
)

val opensansFamily = FontFamily(
    Font(R.font.opensans_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = opensansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = opensansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = opensansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = ptFamily,
        fontWeight = FontWeight.W700,
    ),
    labelMedium = TextStyle(
        fontFamily = ptFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ptFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontSize = 26.sp,
        fontFamily = playfairFamily,
        fontWeight = FontWeight(700),
        textAlign = TextAlign.Center,
    ),
    titleMedium = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.W700,
        fontSize = 26.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = playfairFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)


val Typography.creditCardTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 10.sp,
            fontFamily = workSansFamily,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),
        )
    }
val Typography.creditCardNumberStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 12.sp,
            fontFamily = creditCardFamily,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            letterSpacing = 2.sp,
        )
    }

//FIXME: all these stules should be imported into theme and removed
//FIXME: title, body, .....
val Typography.inputLabelStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 14.sp,
            fontFamily = ptFamily,
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
        )

    }

val Typography.submitButtonTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 15.sp,
            fontFamily = ptFamily,
            fontWeight = FontWeight(700),
            color = MaterialTheme.colorScheme.surface,
            textAlign = TextAlign.Center,
        )

    }
