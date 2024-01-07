package com.example.dzienwydzialu2024.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dzienwydzialu2024.R
import com.example.dzienwydzialu2024.ui.theme.CardPurpleGradient
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    eventType: String,
    eventTitle: String,
    eventPlace: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.height(160.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = Color.Transparent,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.card_background_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = CardPurpleGradient)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .padding(horizontal = 25.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = date,
                                color = Color.White,
                                fontSize = 10.sp,
                                textAlign = TextAlign.End
                            )
                            Text(
                                text = time,
                                color = Color.White,
                                fontSize = 10.sp,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = eventType,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = eventTitle,
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = eventPlace,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(300),
                                letterSpacing = 0.3.sp
                            )
                        }
                        Button(
                            modifier = Modifier
                                .height(30.dp),
                            shape = RoundedCornerShape(10.dp),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_up),
                                color = Color.White,
                                fontSize = 10.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewEventCard() {
//    val startDate = LocalDate.now()
//    val endDate = LocalDate.now()
//
//    val date = startDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("pl", "PL")))
//    val time = "${startDate.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${endDate.format(DateTimeFormatter.ofPattern("HH:mm"))}"

    EventCard(
        date = "31 marca 2024",
        time = "10:00 - 11:30",
        eventType = "Szkolenie",
        eventTitle = "QA - Automatyzacja testów",
        eventPlace = "Sala E104"
    )
}