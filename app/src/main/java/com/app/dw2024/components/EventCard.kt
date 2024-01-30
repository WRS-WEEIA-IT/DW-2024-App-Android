package com.app.dw2024.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
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
import com.app.dw2024.R
import com.app.dw2024.ui.theme.CardPurpleGradient

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
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp,
        backgroundColor = Color.Transparent,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
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
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .padding(horizontal = 25.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = eventType,
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.3.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = eventTitle,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            letterSpacing = 0.3.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = eventPlace,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(300),
                            letterSpacing = 0.3.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween,
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
                        GradientButton(
                            onClick = {  },
                            text = stringResource(id = R.string.sign_up),
                            fontSize = 9.sp,
                        )
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