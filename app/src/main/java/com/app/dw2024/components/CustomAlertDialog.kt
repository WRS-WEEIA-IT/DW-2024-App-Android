package com.app.dw2024.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.dw2024.R
import com.app.dw2024.ui.theme.DarkBlack
import com.app.dw2024.ui.theme.GreenColor
import com.app.dw2024.ui.theme.GreenGradient
import com.app.dw2024.ui.theme.Montserrat
import com.app.dw2024.ui.theme.PurpleGradient

@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    description: String,
    confirmText: String,
    borderWidth: Dp = 1.5.dp,
    borderGradient: Brush = PurpleGradient,
    buttonGradient: Brush = PurpleGradient,
    alpha: Float = 0.9f,
    @DrawableRes icon: Int? = null,
    iconColor: Color = GreenColor
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(15.dp))
                .background(DarkBlack.copy(alpha = alpha))
                .border(
                    width = borderWidth,
                    brush = borderGradient,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = iconColor,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = Montserrat
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = Montserrat
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GradientButton(
                        text = confirmText,
                        width = 180.dp,
                        gradient = buttonGradient,
                        onClick = { onConfirm() }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    CustomAlertDialog(
        onDismiss = { /*TODO*/ },
        onConfirm = { /*TODO*/ },
        title = "Contest results",
        description = "You won!",
        confirmText = "OK",
        alpha = 1f,
        borderWidth = 2.dp,
        borderGradient = GreenGradient,
        buttonGradient = GreenGradient,
        icon = R.drawable.check_image
    )
}