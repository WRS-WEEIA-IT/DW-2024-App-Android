package com.app.dw2024.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.dw2024.R
import com.app.dw2024.components.EventCard
import com.app.dw2024.components.TaskCard
import com.app.dw2024.events.dateFormatter
import com.app.dw2024.events.timeFormatter
import com.app.dw2024.ui.theme.DarkBlack
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current

    Log.d("LOGCAT", "HomeScreen")

    LaunchedEffect(key1 = true) {
        viewModel.refresh()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlack)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(DarkBlack)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.upcoming_events),
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.all_events),
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                content = {
                    items(viewModel.state.events) { event ->
                        val startTimeInstant = Instant.ofEpochSecond(event.timeStart.seconds, event.timeStart.nanoseconds.toLong())
                        val endTimeInstant = Instant.ofEpochSecond(event.timeEnd.seconds, event.timeEnd.nanoseconds.toLong())
                        val startTime = LocalDateTime.ofInstant(startTimeInstant, ZoneId.systemDefault())
                        val endTime = LocalDateTime.ofInstant(endTimeInstant, ZoneId.systemDefault())
                        val date = startTime.format(dateFormatter)
                        val time = "${startTime.format(timeFormatter)} - ${endTime.format(timeFormatter)}"
                        EventCard(
                            modifier = Modifier.width(configuration.screenWidthDp.dp - 64.dp),
                            date = date,
                            time = time,
                            eventType = event.type,
                            eventTitle = event.title,
                            eventPlace = event.hall
                        )
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.available_tasks),
                    color = Color.White,
                    fontSize = 18.sp,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.all_tasks),
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                content = {
                    items(viewModel.state.tasks) { task ->
                        TaskCard(
                            modifier = Modifier.width(configuration.screenWidthDp.dp - 64.dp),
                            id = task.taskNumber,
                            title = task.title,
                            description = task.description,
                            points = task.points,
                            isFinished = task.isFinished,
                            qrCodeImage = R.drawable.ic_qr_code,
                            finishedImage = R.drawable.ic_trophy,
                            imageLabel = stringResource(id = R.string.scan_qr_code_to_complete_task),
                            finishedImageLabel = stringResource(id = R.string.task_completed),
                            onClick = {

                            }
                        )
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            )
        }
    }
}