package com.app.dw2024.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dw2024.MainViewModel
import com.app.dw2024.R
import com.app.dw2024.components.GainedPointsText
import com.app.dw2024.components.TaskCard
import com.app.dw2024.navigation.BottomNavItem
import com.app.dw2024.sendMail
import com.app.dw2024.ui.theme.DarkGrey
import com.app.dw2024.ui.theme.Montserrat

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel
) {
    LaunchedEffect(true) {
        mainViewModel.checkIfUserWonAfterEventAndDisplayDialogMessage()
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkGrey)
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.height(76.dp)
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DarkGrey)
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(id = R.string.available_tasks),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Montserrat
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                GainedPointsText(points = mainViewModel.state.collectedPoints)
            }
            Spacer(modifier = Modifier.height(12.dp))
            val tasks = mainViewModel.state.tasks.sortedBy { it.taskId }
            LazyColumn(
                content = {
                    items(tasks) { task ->
                        TaskCard(
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

                            },
                        )
                    }
                    item { Spacer(modifier = Modifier.height(1.dp)) }
                },
                verticalArrangement = Arrangement.spacedBy(10.dp),
            )
        }
    }
}