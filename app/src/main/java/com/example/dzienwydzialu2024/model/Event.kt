package com.example.dzienwydzialu2024.model

import java.sql.Timestamp
import java.time.LocalDate
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