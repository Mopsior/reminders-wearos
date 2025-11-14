package pl.mopsior.reminders.presentation.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import pl.mopsior.reminders.presentation.ui.components.AddButton
import pl.mopsior.reminders.presentation.ui.components.PhoneButton
import pl.mopsior.reminders.presentation.ui.components.Title

@Composable
fun RemindersListScreen(
    listCount: Int,
    onAddClick: () -> Unit,
) {
    val listState = rememberScalingLazyListState()
    Log.i("MainActivity", "CenterItemIndex: ${listState.centerItemIndex}")
    val visible by remember {
        derivedStateOf {
            listState.centerItemIndex
            listState.layoutInfo.visibleItemsInfo.any { it.index == 0 }
        }
    }

    AnimatedVisibility(
        visible = visible,
        exit = fadeOut(tween(durationMillis = 500)),
        enter = fadeIn(tween(durationMillis = 500))
    ) {
        TimeText(
            modifier = Modifier
                .wrapContentSize()
        )
    }
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

            items(listCount) { index ->
                Text("Reminder $index")
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(4.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AddButton(onAddClick)
                    PhoneButton()
                }
            }
        }
    }
}