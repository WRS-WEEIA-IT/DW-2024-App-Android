package com.app.dw2024.model

import java.time.LocalDateTime

class Event(
    val id: Int,
    val type: String,
    val title: String,
    val place: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val image: String,
)