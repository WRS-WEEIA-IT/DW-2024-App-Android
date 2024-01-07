package com.example.dzienwydzialu2024.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dzienwydzialu2024.R
import com.example.dzienwydzialu2024.ui.theme.BrightPurple
import com.example.dzienwydzialu2024.ui.theme.CardPurpleGradient

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    description: String,
    points: Int,
    isFinished: Boolean,
    @DrawableRes qrCodeImage: Int,
    @DrawableRes finishedImage: Int,
    imageLabel: String,
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
                    .background(brush = CardPurpleGradient),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .padding(horizontal = 25.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            modifier = Modifier
                                .border(
                                    border = BorderStroke(
                                        width = 1.2.dp,
                                        color = BrightPurple
                                    ),
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .padding(horizontal = 15.dp, vertical = 5.5.dp),
                            text = "$points PUNKTÓW",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.2.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Column {
                            Text(
                                text = "Zadanie $id",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = title,
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                letterSpacing = 0.3.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                modifier = Modifier.verticalScroll(rememberScrollState()),
                                text = description,
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight(300),
                                letterSpacing = 0.3.sp
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(66.dp),
                            painter = painterResource(id = R.drawable.ic_qr_code),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            modifier = Modifier.width(66.dp),
                            text = imageLabel,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 7.sp,
                            fontWeight = FontWeight(300),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    TaskCard(
        id = "1",
        title = "Zrób zdjęcie z pracownikiem",
        description = "Zrób zdjęcie z pracownikiem i udostępnij je na Instagramie z hasztagiem #dzienwydzialu2024",
        points = 10,
        isFinished = false,
        qrCodeImage = R.drawable.ic_qr_code,
        finishedImage = R.drawable.ic_trophy,
        imageLabel = "Zeskanuj kod\naby wykonać zadanie"
    )
}