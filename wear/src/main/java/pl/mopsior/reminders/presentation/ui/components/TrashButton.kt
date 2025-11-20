package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.utils.PreviewBox

@Composable
fun TrashButton(
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.error
        ),
    ) {
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = "Remove",
        )
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun TrashButtonPreview() {
    PreviewBox {
        TrashButton()
    }
}
