package com.app.dw2024.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.dw2024.R
import com.app.dw2024.ui.theme.BrightPurple

@Composable
fun GainedPointsText(
    modifier: Modifier = Modifier,
    points: Int
) {
    val firstPart = stringResource(id = R.string.you_gained_x_points).substringBefore("%d")
    val secondPart = stringResource(id = R.string.you_gained_x_points).substringAfter("%d")
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier,
            text = firstPart,
            color = Color.White,
            fontSize = 18.sp,
            letterSpacing = 0.3.sp
        )
        Text(
            modifier = modifier,
            text = points.toString(),
            color = BrightPurple,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.3.sp
        )
        Text(
            modifier = modifier,
            text = secondPart,
            color = Color.White,
            fontSize = 18.sp,
            letterSpacing = 0.3.sp
        )
    }
}