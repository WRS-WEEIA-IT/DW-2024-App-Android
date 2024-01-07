package com.app.dw2024.model

data class Task(
    val taskNumber: Int = 0,
    val title: String = "",
    val description: String = "",
    val points: Int = 0,
    val isFinished: Boolean = false,
    val imageSource: String = "",
    val qrCode: String = "",
)