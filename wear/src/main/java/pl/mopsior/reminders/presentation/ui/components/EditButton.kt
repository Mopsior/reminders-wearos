package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.utils.PreviewBox

@Composable
fun EditButton(
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    isSecondary: Boolean = false
) {
    Button(
        onClick = { onClick() },
        enabled = enabled,
        colors =
            if (isSecondary) ButtonDefaults.secondaryButtonColors()
            else ButtonDefaults.buttonColors()
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
        )
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun EditButtonPreview() {
    PreviewBox {
        EditButton()
    }
}
