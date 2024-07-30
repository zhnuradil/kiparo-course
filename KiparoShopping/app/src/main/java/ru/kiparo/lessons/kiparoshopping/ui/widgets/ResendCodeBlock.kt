package ru.kiparo.lessons.kiparoshopping.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.kiparo.lessons.kiparoshopping.R
import ru.kiparo.lessons.kiparoshopping.ui.theme.space_8

@Composable
fun ResendCodeBlock(onResend: () -> Unit) {
    Text(
        text = stringResource(id = R.string.have_not_received_code),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = Modifier.clickable(onClick = onResend)
    )

    Spacer(modifier = Modifier.height(space_8))

    Text(
        text = stringResource(id = R.string.resend),
        style = MaterialTheme.typography.labelLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clickable(onClick = onResend)
    )
}