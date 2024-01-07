package com.app.dw2024.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.app.dw2024.R
import com.app.dw2024.components.EventCard
import com.app.dw2024.ui.theme.DarkBlack
import java.time.format.DateTimeFormatter
import java.util.Locale

val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("pl", "PL"))
val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    viewModel: EventsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlack)
            .padding(horizontal = 16.dp, vertical = 8.dp),
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
                // TODO display drop down menu
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.filter),
                    tint = Color.White
                )
            }
        }
        LazyColumn(
            content = {
                items(viewModel.state.events) { event ->
                    val date = event.startDate.format(dateFormatter)
                    val time = "${event.startDate.format(timeFormatter)} - ${event.endDate.format(timeFormatter)}"
                    EventCard(
                        date = date,
                        time = time,
                        eventType = event.type,
                        eventTitle = event.title,
                        eventPlace = event.place
                    )
                }
            },
            verticalArrangement = Arrangement.spacedBy(10.dp),
        )
    }
}