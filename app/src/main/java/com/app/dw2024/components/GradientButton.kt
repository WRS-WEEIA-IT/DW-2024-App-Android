package com.app.dw2024.components

import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.Dp
import com.app.dw2024.ui.theme.PurpleGradient
import androidx.compose.foundation.background
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

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush = PurpleGradient,
    textStyle: TextStyle = MaterialTheme.typography.button,
    isEnabled: Boolean = true,
    width: Dp = 100.dp,
    fontSize: TextUnit = 16.sp
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        shape = RoundedCornerShape(100.dp),
        enabled = isEnabled,
        modifier = modifier,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .clip(RoundedCornerShape(100.dp))
                .background(gradient)
                .padding(vertical = 16.dp, horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = textStyle,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}