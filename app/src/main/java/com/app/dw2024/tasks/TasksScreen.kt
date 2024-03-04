package com.app.dw2024.tasks

import android.util.Log
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.dw2024.R
import com.app.dw2024.components.GainedPointsText
import com.app.dw2024.components.TaskCard
import com.app.dw2024.ui.theme.DarkGrey

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        Log.d("LOGCAT", "Refresh!")
        viewModel.refresh()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkGrey)
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
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            GainedPointsText(points = viewModel.state.collectedPoints)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            content = {
                items(viewModel.state.tasks) { task ->
                    TaskCard(
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
                item { Spacer(modifier = Modifier.height(1.dp)) }
            },
            verticalArrangement = Arrangement.spacedBy(10.dp),
        )
    }
}