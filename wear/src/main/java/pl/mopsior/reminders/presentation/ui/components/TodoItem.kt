package pl.mopsior.reminders.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import pl.mopsior.reminders.presentation.data.entities.TodoEntity

@Composable

fun TodoItem(
    todo: TodoEntity,
    onToggleCompleted: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    ToggleChip(
        checked = todo.isCompleted,
        onCheckedChange = { onToggleCompleted() },
        label = {
            Text(
                text = todo.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textDecoration = if (todo.isCompleted) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
        },
        toggleControl = {
            Icon(
                imageVector = if (todo.isCompleted) {
                    Icons.Default.CheckCircle
                } else {
                    Icons.Default.RadioButtonUnchecked
                },
                contentDescription = if (todo.isCompleted) {
                    "Done"
                } else {
                    "Not done"
                }
            )
        },
        modifier = modifier
    )
}