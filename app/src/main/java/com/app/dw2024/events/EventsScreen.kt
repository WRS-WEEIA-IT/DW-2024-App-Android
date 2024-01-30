package com.app.dw2024.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.dw2024.R
import com.app.dw2024.components.EventCard
import com.app.dw2024.ui.theme.DarkGrey
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("pl", "PL"))
val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    viewModel: EventsViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(key1 = true) {
        viewModel.refresh()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
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
            IconButton(onClick = {
                viewModel.onEvent(EventsEvent.OnBottomModalSheetShow)
            }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = stringResource(id = R.string.map),
                    tint = Color.White
                )
            }
        }
        LazyColumn(
            content = {
                items(viewModel.state.events) { event ->
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
                item { Spacer(modifier = Modifier.height(1.dp)) }
            },
            verticalArrangement = Arrangement.spacedBy(10.dp),
        )
    }

    if (viewModel.state.showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                viewModel.onEvent(EventsEvent.OnBottomModalSheetDismiss)
            },
            containerColor = DarkGrey,
        ) {
            Image(
                painter = painterResource(id = R.drawable.card_background_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}