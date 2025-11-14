package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme

@Composable
fun PhoneButton() {
    Button(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    ) {
        Icon(
            imageVector = Icons.Default.Smartphone,
            contentDescription = "Add",
        )
    }
}