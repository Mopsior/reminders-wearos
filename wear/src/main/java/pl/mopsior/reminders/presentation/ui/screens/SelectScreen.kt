package pl.mopsior.reminders.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Checkbox
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import pl.mopsior.reminders.presentation.data.entities.TodoEntity
import pl.mopsior.reminders.presentation.data.viewModel.TodoViewModel
import pl.mopsior.reminders.presentation.theme.RemindersTheme
import pl.mopsior.reminders.presentation.ui.components.EditButton
import pl.mopsior.reminders.presentation.ui.components.Title
import pl.mopsior.reminders.presentation.ui.components.TrashButton
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun SelectScreen(
    viewModel: TodoViewModel? = null,
    isPreview: Boolean = false,
    previewTodos: List<TodoEntity>? = null
) {
    val todos: List<TodoEntity> = if (isPreview) {
        previewTodos ?: exampleTodosList
    } else {
        val vm = viewModel ?: throw IllegalArgumentException("viewModel must be provided when not in preview")
        vm.recentTodos.collectAsStateWithLifecycle().value
    }

    val listState = rememberScalingLazyListState()

    val selectedTodos = remember { mutableStateMapOf<Long, Boolean>()}

    LaunchedEffect(todos) {
        todos.forEach { todo ->
            if (!selectedTodos.containsKey(key = todo.id)) {
                selectedTodos[todo.id] = false
            }
        }
    }

    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
    ) {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            autoCentering = null,
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            item {
                ListHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Title("Select reminders")
                }
            }

            if (todos.isEmpty()) {
                item {
                    Text(
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
                    val isChecked = selectedTodos[todo.id] ?: false
                    ToggleChip(
                        label = {
                            Text(
                                text = todo.title,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2
                            )
                        },
                        checked = isChecked,
                        onCheckedChange = { newValue ->
                            selectedTodos[todo.id] = newValue
                        },
                        toggleControl = {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { newValue ->
                                    selectedTodos[todo.id] = newValue
                                }
                            )
                        },
                        colors = ToggleChipDefaults.toggleChipColors(
                            uncheckedToggleControlColor = ToggleChipDefaults.SwitchUncheckedIconColor
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.Bottom
        ) {
            EditButton(
                onClick = {
                    Log.i("SelectScreen > EditButton", "Selected todos: ${selectedTodos.filter { it.value }.keys}")
                },
                enabled = selectedTodos.filter { it.value }.keys.toList().size < 2
            )
            TrashButton(onClick = {
                val selected = selectedTodos.filter { it.value }.keys.toList()
                // jeśli nie w trybie preview - wywołaj ViewModel do usunięcia
                if (!isPreview) {
                    val vm = viewModel ?: throw IllegalArgumentException("viewModel must be provided when not in preview")
                    vm.deleteTodosByIds(selected)
                    // Usuń lokalne zaznaczenia od razu, UI się odświeży dzięki StateMap
                    selected.forEach { id -> selectedTodos.remove(id) }
                } else {
                    Log.i("SelectScreen > TrashButton", "Preview delete of: $selected")
                }

            })
        }
    }
}

@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun SelectScreenPreview() {
    RemindersTheme {
        SelectScreen(
            isPreview = true
        )
    }
}
