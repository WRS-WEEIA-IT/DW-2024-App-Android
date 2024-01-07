package com.app.dw2024.model

import androidx.annotation.DrawableRes

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val points: Int,
    val isFinished: Boolean,
    @DrawableRes val image: Int
)