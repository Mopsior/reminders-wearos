// !! TO BE REMOVED !!
// Keeped for reference of RemoteInput and double-width button usage

package pl.mopsior.reminders.presentation.ui.screens

import android.app.Activity
import android.app.RemoteInput
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.input.wearableExtender
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.theme.RemindersTheme

@Composable
fun AddReminderScreen() {
    val defaultText = "Enter reminder text"
    var userInput by remember { mutableStateOf(defaultText) }
    var inputTextKey = "input_text"
    
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val results = RemoteInput.getResultsFromIntent(data)
                val newInputText = results?.getCharSequence(inputTextKey)
                inputTextKey = newInputText?.toString() ?: defaultText
            }
        }
    }

//    val remoteInputs: List<RemoteInput> = listOf(
//        RemoteInput.Builder(inputTextKey)
//            .setLabel(defaultText)
//            .wearableExtender {
//                setEmojisAllowed(true)
//                setInputActionType(EditorInfo.IME_ACTION_DONE)
//            }.build()
//    )

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "New reminder",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement
                .spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(ButtonDefaults.DefaultButtonSize * 2)
                    .padding(bottom = ButtonDefaults.DefaultButtonSize/2)
            ) {
                Icon(
                    imageVector = Icons.Default.Keyboard,
                    contentDescription = "Use keyboard",
                )
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = androidx.wear.compose.material.MaterialTheme.colors.surface
                ),
                modifier = Modifier
                    .padding(bottom = ButtonDefaults.DefaultButtonSize/2)
            ) {
                Icon(
                    imageVector = Icons.Default.Smartphone,
                    contentDescription = "Use phone",
                )
            }
        }
    }

}

@Preview(name = "Add Reminder", device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun AddReminderPreview() {
    RemindersTheme {
        AddReminderScreen()
    }
}