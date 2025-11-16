package pl.mopsior.reminders.presentation.ui.components

import android.app.Activity
import android.app.RemoteInput
import android.content.Intent
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender

@Composable
fun AddButton(onAdd: (String) -> Unit) {
    val defaultText = "Enter reminder text"
    var userInput by remember { mutableStateOf(defaultText) }
    val inputTextKey = "input_text"

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.i("AddButton", "Activity result received")
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                val results = RemoteInput.getResultsFromIntent(data)
                val newInputText = results?.getCharSequence(inputTextKey)
                userInput = newInputText?.toString() ?: defaultText
                Log.i("AddButton", "User input: $userInput")
                onAdd(userInput)
            }
        }
    }

    Button(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp),
        onClick = {
            val remoteInput = RemoteInput.Builder(inputTextKey)
                .setLabel(defaultText)
                .wearableExtender {
                    setEmojisAllowed(true)
                    setInputActionType(EditorInfo.IME_ACTION_DONE)
                }.build()
            val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
            RemoteInputIntentHelper.putRemoteInputsExtra(intent, listOf(remoteInput))

            launcher.launch(intent)
        },
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
        )
    }
}