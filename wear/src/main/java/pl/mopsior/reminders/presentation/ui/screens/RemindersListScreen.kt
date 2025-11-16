package pl.mopsior.reminders.presentation.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.WearApp
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.ui.components.AddButton
import pl.mopsior.reminders.presentation.ui.components.PhoneButton
import pl.mopsior.reminders.presentation.ui.components.Title
import pl.mopsior.reminders.presentation.ui.components.TodoItem

@Composable
fun RemindersListScreen(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier
) {
//  collectAsStateWithLifecycle subskrybuje i odsubskrybuje automatycznie
//  by - pozwala na użycie bez .value
    val todos by viewModel.allTodos.collectAsStateWithLifecycle()

    val listState = rememberScalingLazyListState()

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
                        .padding(top = 8.dp)
                ) {
                    Title()
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
                            viewModel.toggleTodoCompleted(todo)
                        },
                        onDelete = {
                            viewModel.deleteTodo(todo)
                        }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(4.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AddButton(onAdd = { title ->
                        Log.i("RemindersListScreen", "Adding todo: $title")
                        viewModel.addTodo(title)
                    })
                    PhoneButton()
                }
            }
        }
    }
}

@Preview(name = "Main Screen", device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    WearApp()
}