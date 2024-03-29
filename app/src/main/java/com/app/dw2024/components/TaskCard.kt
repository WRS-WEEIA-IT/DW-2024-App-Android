package com.app.dw2024.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dw2024.R
import com.app.dw2024.ui.theme.BrightPurple
import com.app.dw2024.ui.theme.CardPurpleGradient
import com.app.dw2024.ui.theme.Montserrat
import com.app.dw2024.util.MapImageSourceToDrawableRes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    id: Int,
    title: String,
    description: String,
    points: Int,
    isFinished: Boolean,
    @DrawableRes qrCodeImage: Int,
    @DrawableRes finishedImage: Int,
    imageLabel: String,
    finishedImageLabel: String,
    imageSrc: String,
    onClick: () -> Unit = {}
) {
    val iconRes = if (isFinished) finishedImage else qrCodeImage
    val iconLabel = if (isFinished) finishedImageLabel else imageLabel

    Card(
        modifier = modifier
            .height(160.dp)
            .clickable(false) {
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp,
        backgroundColor = Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val imageResId = MapImageSourceToDrawableRes.taskImagesMap[imageSrc] ?: R.drawable.taskphoto4
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = imageResId),
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
                        .padding(end = 25.dp, top = 15.dp, start = 15.dp, bottom = 15.dp),
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
                        if (id != -1) {
                            Text(
                                modifier = Modifier
                                    .border(
                                        border = BorderStroke(
                                            width = 1.2.dp,
                                            color = BrightPurple
                                        ),
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(horizontal = 15.dp, vertical = 6.5.dp),
                                text = stringResource(id = R.string.x_points, points),
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.2.sp,
                                fontFamily = Montserrat
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Column {
                            if (id != -1) {
                                Text(
                                    text = stringResource(id = R.string.task_x, id),
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    letterSpacing = 0.3.sp,
                                    fontFamily = Montserrat
                                )
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = title,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 0.3.sp,
                                fontFamily = Montserrat
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = description,
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 0.3.sp,
                                fontFamily = Montserrat
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
                            painter = painterResource(id = iconRes),
                            contentDescription = null
                        )
                        if (id != -1) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                modifier = Modifier.width(66.dp),
                                text = iconLabel,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight(300),
                                fontFamily = Montserrat
                            )
                        }
                    }
                }
            }
        }
        if (isFinished) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.65f))
            )
        }
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    TaskCard(
        id = 1,
        title = "Zrób zdjęcie z pracownikiem",
        description = "Zrób zdjęcie z pracownikiem i udostępnij je na Instagramie z hasztagiem #dzienwydzialu2024",
        points = 10,
        isFinished = false,
        qrCodeImage = R.drawable.qr_code_image,
        finishedImage = R.drawable.check_image,
        imageLabel = "Zeskanuj kod\naby wykonać zadanie",
        finishedImageLabel = "Zadanie wykonane!",
        imageSrc = "taskphoto1"
    )
}