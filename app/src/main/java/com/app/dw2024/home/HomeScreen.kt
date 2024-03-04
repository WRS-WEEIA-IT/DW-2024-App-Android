package com.app.dw2024.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.dw2024.MainViewModel
import com.app.dw2024.R
import com.app.dw2024.components.EventCard
import com.app.dw2024.components.TaskCard
import com.app.dw2024.util.Constants
import com.app.dw2024.events.dateFormatter
import com.app.dw2024.events.timeFormatter
import com.app.dw2024.navigation.BottomNavItem
import com.app.dw2024.ui.theme.CardPurpleGradient
import com.app.dw2024.ui.theme.DarkGrey
import com.app.dw2024.ui.theme.Montserrat
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    mainViewModel: MainViewModel
) {
    val configuration = LocalConfiguration.current
    val uriHandler = LocalUriHandler.current
    val pagerState = rememberPagerState { viewModel.state.pagerImages.size }
    var currentPage by remember { mutableIntStateOf(0) }

    LaunchedEffect(true) {
        mainViewModel.checkIfUserWonAfterEventAndDisplayDialogMessage()
    }

    LaunchedEffect(true) {
        while (true) {
            delay(2000)
            currentPage = (currentPage + 1) % viewModel.state.pagerImages.size
            pagerState.animateScrollToPage(currentPage, animationSpec = tween(500))
            if (currentPage == viewModel.state.pagerImages.size - 1) {
                delay(200)
                pagerState.scrollToPage(0)
                currentPage = 0
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.height(configuration.screenHeightDp.dp / 2)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = viewModel.state.pagerImages[it]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = CardPurpleGradient)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(vertical = 16.dp, horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier.height(76.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(DarkGrey)
                .align(Alignment.BottomCenter)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.upcoming_event),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Montserrat
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
                        text = stringResource(id = R.string.see_all),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = Montserrat
                    )
                    Spacer(modifier = Modifier.width(4.dp))
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
                val currentTimeInSeconds = System.currentTimeMillis() / 1000
                val event = (mainViewModel.state.lectures + mainViewModel.state.workshops)
                    .filter { currentTimeInSeconds < it.timeStart.seconds }
                    .minByOrNull { it.timeStart }
                if (event != null) {
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
                        eventPlace = event.room,
                        onClick = {
                            uriHandler.openUri(Constants.FORMS_URL)
                        },
                        imageSrc = event.imageSrc
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {

                    }
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
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Montserrat
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
                        text = stringResource(id = R.string.see_all),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = Montserrat
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            val availableTasks = mainViewModel.state.tasks.filter { !it.isFinished }
            if (mainViewModel.state.tasks.isNotEmpty() && availableTasks.isEmpty()) {
                TaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    id = -1,
                    title = stringResource(id = R.string.you_completed_all_tasks),
                    description = stringResource(id = R.string.congratulations),
                    points = -1,
                    isFinished = false,
                    qrCodeImage = R.drawable.qr_code_image,
                    finishedImage = R.drawable.check_image,
                    imageLabel = stringResource(id = R.string.scan_qr_code_to_complete_task),
                    finishedImageLabel = stringResource(id = R.string.task_completed),
                    imageSrc = "all_tasks_finished_background",
                    onClick = {

                    }
                )
            } else {
                LazyRow(
                    modifier = Modifier.height(160.dp),
                    content = {
                        items(availableTasks) { task ->
                            TaskCard(
                                modifier = Modifier.width(configuration.screenWidthDp.dp - 64.dp),
                                id = task.taskId,
                                title = task.title,
                                description = task.description,
                                points = task.points,
                                isFinished = task.isFinished,
                                qrCodeImage = R.drawable.qr_code_image,
                                finishedImage = R.drawable.check_image,
                                imageLabel = stringResource(id = R.string.scan_qr_code_to_complete_task),
                                finishedImageLabel = stringResource(id = R.string.task_completed),
                                imageSrc = task.imageSrc,
                                onClick = {

                                }
                            )
                        }
                    },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}