package pl.mopsior.reminders.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.SendToMobile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.utils.PreviewBox

@Composable
fun PhoneButton(
    modifier: Modifier = Modifier,
    extraWidth: Dp = 0.dp,
) {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .width(ButtonDefaults.DefaultButtonSize * 2 + extraWidth)
            .padding(top = 8.dp)
        .then(modifier),
        onClick = {
            Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.SendToMobile,
            contentDescription = "Add",
        )
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun PhoneButtonPreview() {
    PreviewBox{
        PhoneButton()
    }
}