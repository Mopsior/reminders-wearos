package pl.mopsior.reminders.presentation.ui.components

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.tooling.preview.devices.WearDevices
import pl.mopsior.reminders.presentation.WearApp
import pl.mopsior.reminders.presentation.data.entities.TodoEntity
import pl.mopsior.reminders.presentation.theme.RemindersTheme
import pl.mopsior.reminders.presentation.ui.screens.RemindersListScreen

@Composable

//fun TodoItem(
//    todo: TodoEntity,
//    onToggleCompleted: () -> Unit,
//    onDelete: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    ToggleChip(
//        checked = todo.isCompleted,
//        onCheckedChange = { onToggleCompleted() },
//        label = {
//            Text(
//                text = todo.title,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                textDecoration = if (todo.isCompleted) {
//                    TextDecoration.LineThrough
//                } else {
//                    TextDecoration.None
//                }
//            )
//        },
//        toggleControl = {
//            Icon(
//                imageVector = if (todo.isCompleted) {
//                    Icons.Default.CheckCircle
//                } else {
//                    Icons.Default.RadioButtonUnchecked
//                },
//                contentDescription = if (todo.isCompleted) {
//                    "Done"
//                } else {
//                    "Not done"
//                }
//            )
//        },
//        modifier = modifier
//    )
//}

fun TodoItem(
    todo: TodoEntity,
    lastItem: Boolean = false,
    onToggleCompleted: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 18.dp,
                end = 18.dp,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onToggleCompleted()
            },
    ) {
        Row(
            modifier = Modifier.padding(
                start = 4.dp,
                end = 4.dp,
                top = 6.dp,
                bottom = 8.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Crossfade(
                targetState = todo.isCompleted,
                animationSpec = tween(durationMillis = 100),
                label = "checkbox crossfade"
            ) { isCompleted ->
                Icon(
                    imageVector = if (isCompleted) Icons.Default.CheckBox
                    else Icons.Default.CheckBoxOutlineBlank,
                    contentDescription = if (isCompleted) "Checked" else "Unchecked",
                    tint = if (isCompleted) Color.Gray else Color.White,
                )
            }
            Text(
                text = todo.title,
                maxLines = 2,
                color =
                    if (todo.isCompleted) Color.Gray
                    else Color.White,
                textDecoration =
                    if (todo.isCompleted) TextDecoration.LineThrough
                    else TextDecoration.None,
                fontSize = 16.sp,
                lineHeight = 1.4.em
            )
        }
//        if (!lastItem) {
//            HorizontalDivider(
//                color = Color.DarkGray,
//                thickness = 1.dp,
//            )
//        }
    }
}


@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Composable
fun TodosScreenPreview() {
    RemindersTheme {
        RemindersListScreen(
            isPreview = true
        )
    }
}