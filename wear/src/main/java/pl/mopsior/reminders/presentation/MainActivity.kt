package pl.mopsior.reminders.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.theme.RemindersTheme
import pl.mopsior.reminders.presentation.ui.navigation.AppNavigation
import pl.mopsior.reminders.presentation.ui.screens.AddReminderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp(20)
        }
    }
}

@Composable
fun WearApp(listCount: Int) {
    RemindersTheme {
        AppNavigation(listCount)
    }
}

@Preview(name = "Main Screen", device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    WearApp(3)
}

@Preview(name = "Add Reminder", device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun AddReminderPreview() {
    RemindersTheme {
        AddReminderScreen()
    }
}