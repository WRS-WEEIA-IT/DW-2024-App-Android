package com.app.dw2024.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
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
import androidx.navigation.NavController
import com.app.dw2024.R
import com.app.dw2024.components.EventCard
import com.app.dw2024.components.TaskCard
import com.app.dw2024.events.dateFormatter
import com.app.dw2024.events.timeFormatter
import com.app.dw2024.navigation.BottomNavItem
import com.app.dw2024.ui.theme.DarkGrey
import com.app.dw2024.ui.theme.DeepPurple
import com.google.api.Distribution.BucketOptions.Linear
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = true) {
        viewModel.refresh()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(DarkGrey)
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
                    modifier = Modifier.clickable {
                        navController.navigate(BottomNavItem.Events.route) {
                            launchSingleTop = true
                            popUpTo(BottomNavItem.Events.route) {
                                inclusive = true
                            }
                        }
                    },
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
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.state.events.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(6.dp).offset(y = (-8).dp),
                        color = DeepPurple
                    )
                } else {
                    val event = viewModel.state.events.first()
                    val startTimeInstant = Instant.ofEpochSecond(event.timeStart.seconds, event.timeStart.nanoseconds.toLong())
                    val endTimeInstant = Instant.ofEpochSecond(event.timeEnd.seconds, event.timeEnd.nanoseconds.toLong())
                    val startTime = LocalDateTime.ofInstant(startTimeInstant, ZoneId.systemDefault())
                    val endTime = LocalDateTime.ofInstant(endTimeInstant, ZoneId.systemDefault())
                    val date = startTime.format(dateFormatter)
                    val time = "${startTime.format(timeFormatter)} - ${endTime.format(timeFormatter)}"
                    EventCard(
                        date = date,
                        time = time,
                        eventType = event.type,
                        eventTitle = event.title,
                        eventPlace = event.hall
                    )
                }
            }
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
                    modifier = Modifier.clickable {
                        navController.navigate(BottomNavItem.Tasks.route) {
                            launchSingleTop = true
                            popUpTo(BottomNavItem.Tasks.route) {
                                inclusive = true
                            }
                        }
                    },
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
                modifier = Modifier.height(160.dp),
                content = {
                    if (viewModel.state.tasks.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .height(160.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.height(6.dp).offset(y = (-8).dp),
                                    color = DeepPurple
                                )
                            }
                        }
                    } else {
                        items(viewModel.state.tasks) { task ->
                            TaskCard(
                                modifier = Modifier.width(configuration.screenWidthDp.dp - 64.dp),
                                id = task.taskNumber,
                                title = task.title,
                                description = task.description,
                                points = task.points,
                                isFinished = task.isFinished,
                                qrCodeImage = R.drawable.qr_code_image,
                                finishedImage = R.drawable.check_image,
                                imageLabel = stringResource(id = R.string.scan_qr_code_to_complete_task),
                                finishedImageLabel = stringResource(id = R.string.task_completed),
                                onClick = {

                                }
                            )
                        }
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}