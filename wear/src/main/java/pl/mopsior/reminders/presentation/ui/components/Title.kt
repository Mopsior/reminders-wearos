package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import pl.mopsior.reminders.R

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.title),
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
    )
}