package pl.mopsior.reminders.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.ui.screens.AddReminderScreen
import pl.mopsior.reminders.presentation.ui.screens.RemindersListScreen
import pl.mopsior.reminders.presentation.ui.screens.SelectScreen

@Composable
fun AppNavigation(viewModel: TodoViewModel) {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "reminders_list"
    ) {
        composable("reminders_list") {
            RemindersListScreen(
                viewModel = viewModel,
                selectRedirect = {
                    navController.navigate("select")
                }
            )
        }

        composable("add_reminder") {
            AddReminderScreen()
        }

        composable("select") {
            SelectScreen(
                viewModel = viewModel
            )
        }
    }
}