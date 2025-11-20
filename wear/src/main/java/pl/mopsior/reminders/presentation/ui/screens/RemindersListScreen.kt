package pl.mopsior.reminders.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.R
import pl.mopsior.reminders.presentation.data.entities.TodoEntity
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.theme.RemindersTheme
import pl.mopsior.reminders.presentation.ui.components.AddButton
import pl.mopsior.reminders.presentation.ui.components.EditButton
import pl.mopsior.reminders.presentation.ui.components.PhoneButton
import pl.mopsior.reminders.presentation.ui.components.Title
import pl.mopsior.reminders.presentation.ui.components.TodoItem

val exampleTodosList = listOf(
    TodoEntity(
        id = 1L,
        title = "Buy milk"
    ),
    TodoEntity(
        id = 2L,
        title = "Send a very long email to a very important customer",
        isCompleted = true,
        completedAt = System.currentTimeMillis()
    ),
    TodoEntity(
        id = 3L,
        title = "Walk the dog"
    )
)

@Composable
fun RemindersListScreen(
    viewModel: TodoViewModel? = null,
    isPreview: Boolean = false,
    previewTodos: List<TodoEntity>? = null,
    selectRedirect: () -> Unit = { }
) {
    // daje previewTodos lub przykładową listę gdy isPreview = true, inaczej viewmodel
    val todos: List<TodoEntity> = if (isPreview) {
        previewTodos ?: exampleTodosList
    } else {
        val vm = viewModel ?: throw IllegalArgumentException("viewModel must be provided when not in preview")
        vm.recentTodos.collectAsStateWithLifecycle().value
    }

    val listState = rememberScalingLazyListState()
    val navController = rememberSwipeDismissableNavController()

//  główny scaffold aplikacji
    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
    ) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            autoCentering = null,

        ) {
            item {
                ListHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Title(text = stringResource(R.string.title))
                }
            }

            if (todos.isEmpty()) {
                item {
                    Text (
                        text = "No reminders yet",
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(
                    count = todos.size,
                    key = { index -> todos[index].id }
                ) { index ->
                    val todo = todos[index]
                    TodoItem(
                        todo = todo,
                        onToggleCompleted = {
                            viewModel?.toggleTodoCompleted(todo)
                        },
                        lastItem = index == todos.size - 1
                    )
                }
            }

            val gap = 4.dp

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 0.dp),
                    horizontalArrangement = Arrangement
                        .spacedBy(gap, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AddButton(onAdd = { title ->
                        Log.i("RemindersListScreen", "Adding todo: $title")
                        viewModel?.addTodo(title)
                    })
                    EditButton(
                        isSecondary = true,
                        onClick = {
                            selectRedirect()
                        }
                    )
                }
            }

            item {
                PhoneButton(
                    extraWidth = gap,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    RemindersTheme {
        RemindersListScreen(
            isPreview = true
        )
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun EmptyListPreview() {
    RemindersTheme {
        RemindersListScreen(
            isPreview = true,
            previewTodos = emptyList()
        )
    }
}
