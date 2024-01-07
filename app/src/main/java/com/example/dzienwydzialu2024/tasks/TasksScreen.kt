package com.example.dzienwydzialu2024.tasks

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dzienwydzialu2024.R
import com.example.dzienwydzialu2024.components.EventCard
import com.example.dzienwydzialu2024.components.GainedPointsText
import com.example.dzienwydzialu2024.components.TaskCard
import com.example.dzienwydzialu2024.events.dateFormatter
import com.example.dzienwydzialu2024.events.timeFormatter
import com.example.dzienwydzialu2024.ui.theme.DarkBlack

@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlack)
            .padding(horizontal = 16.dp, vertical = 8.dp),
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
                        id = task.id,
                        title = task.title,
                        description = task.description,
                        points = task.points,
                        isFinished = task.isFinished,
                        qrCodeImage = R.drawable.ic_qr_code,
                        finishedImage = R.drawable.ic_trophy,
                        imageLabel = stringResource(id = R.string.scan_qr_code_to_complete_task),
                        onClick = {

                        }
                    )
                }
            },
            verticalArrangement = Arrangement.spacedBy(10.dp),
        )
    }
}