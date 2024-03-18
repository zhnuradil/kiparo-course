package ru.kiparo.lessons.kiparoshopping.ui.widgets

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import ru.kiparo.lessons.kiparoshopping.ui.theme.KiparoShoppingTheme
import ru.kiparo.lessons.kiparoshopping.ui.theme.item_height_45
import ru.kiparo.lessons.kiparoshopping.ui.theme.submitButtonTextStyle
import ru.kiparo.lessons.kiparoshopping.ui.theme.zero_val

@Composable
fun DefaultButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(zero_val),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface
        )
    ) {
        Text(
            title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultButtonPreview() {
    KiparoShoppingTheme {
        DefaultButton("Default button", onClick = {}, modifier = Modifier.fillMaxWidth())
    }
}