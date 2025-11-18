package pl.mopsior.reminders.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.mopsior.reminders.presentation.theme.RemindersTheme

@Composable
fun PreviewBox(content: @Composable () -> Unit) {
    RemindersTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}