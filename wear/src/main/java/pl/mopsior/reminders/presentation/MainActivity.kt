package pl.mopsior.reminders.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModelFactory
import pl.mopsior.reminders.presentation.theme.RemindersTheme
import pl.mopsior.reminders.presentation.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    val application = LocalContext.current
        .applicationContext as TodoApplication

    val factory = TodoViewModelFactory(application.repository)

    val viewModel: TodoViewModel = viewModel(factory = factory)

    RemindersTheme {
        AppNavigation(viewModel = viewModel)
    }
}

@Preview(name = "Main Screen", device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    WearApp()
}