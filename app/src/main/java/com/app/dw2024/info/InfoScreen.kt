package com.app.dw2024.info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.dw2024.R
import com.app.dw2024.ui.theme.DarkGrey
import com.app.dw2024.ui.theme.Montserrat
import com.app.dw2024.ui.theme.PurpleGradient

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoViewModel = hiltViewModel()
) {
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
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(id = R.string.information),
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = Montserrat
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.app_id, viewModel.state.userId ?: "0"),
            color = Color.Gray,
            fontSize = 15.sp,
            fontFamily = Montserrat
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.hello_welcome_to_department_day_app),
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = Montserrat
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.nice_to_see_you),
            color = Color.Gray,
            fontSize = 15.sp,
            fontFamily = Montserrat
        )
        Text(
            text = stringResource(id = R.string.complete_tasks_earn_points_claim_rewards),
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontFamily = Montserrat
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 3.dp,
                        brush = PurpleGradient
                    ),
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(horizontal = 26.dp, vertical = 12.dp),
            text = stringResource(id = R.string.you_earned_x_points, viewModel.state.collectedPoints),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.2.sp,
            fontFamily = Montserrat
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = stringResource(id = R.string.organizers),
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = Montserrat
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.weeia_logo), contentDescription = null)
            Image(painter = painterResource(id = R.drawable.wrs_weeia_logo), contentDescription = null)
        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Text(
//            text = stringResource(id = R.string.partners),
//            color = Color.White,
//            fontSize = 18.sp,
//            fontFamily = Montserrat
//        )
//        Spacer(modifier = Modifier.height(8.dp))
    }
}