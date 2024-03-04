package com.app.dw2024.components

import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.Dp
import com.app.dw2024.ui.theme.PurpleGradient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.app.dw2024.ui.theme.Montserrat

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush = PurpleGradient,
    textStyle: TextStyle = MaterialTheme.typography.button,
    isEnabled: Boolean = true,
    width: Dp = 100.dp,
    fontSize: TextUnit = 16.sp,
    cornerShape: RoundedCornerShape = RoundedCornerShape(100.dp)
) {
    Box(
        modifier = modifier
            .width(width)
            .clip(cornerShape)
            .background(gradient)
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
            .padding(vertical = fontSize.value.dp, horizontal = 2 * fontSize.value.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontFamily = Montserrat
        )
    }
}