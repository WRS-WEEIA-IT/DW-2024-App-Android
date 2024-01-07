package com.example.dzienwydzialu2024.model

import java.sql.Timestamp
import java.time.LocalDate

class Event(
    val id: Int,
    val type: String,
    val title: String,
    val place: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val image: String,
)