package pl.mopsior.reminders.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.ui.screens.AddReminderScreen
import pl.mopsior.reminders.presentation.ui.screens.RemindersListScreen

@Composable
fun AppNavigation(viewModel: TodoViewModel) {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "reminders_list"
    ) {
        composable("reminders_list") {
            RemindersListScreen(
                viewModel = viewModel
            )
        }

        composable("add_reminder") {
            AddReminderScreen()
        }
    }
}