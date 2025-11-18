package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.R
import pl.mopsior.reminders.presentation.utils.PreviewBox

@Composable
fun Title() {
    Text(
        text = stringResource(R.string.title),
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun TitleComponentPreview() {
    PreviewBox {
        Title()
    }
}