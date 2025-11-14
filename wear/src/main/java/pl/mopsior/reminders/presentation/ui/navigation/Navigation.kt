package pl.mopsior.reminders.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import pl.mopsior.reminders.presentation.ui.screens.AddReminderScreen
import pl.mopsior.reminders.presentation.ui.screens.RemindersListScreen

@Composable
fun AppNavigation(listCount: Int) {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "reminders_list"
    ) {
        composable("reminders_list") {
            RemindersListScreen(
                listCount = listCount,
                onAddClick =  {
                    navController.navigate("add_reminder")
                }
            )
        }

        composable("add_reminder") {
            AddReminderScreen()
        }
    }
}