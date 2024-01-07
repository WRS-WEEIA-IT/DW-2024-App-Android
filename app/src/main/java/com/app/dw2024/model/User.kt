package com.app.dw2024.model

import com.google.firebase.Timestamp

data class User(
    val id: Int = 0,
    val points: Int = 0,
    val time: Timestamp = Timestamp.now(),
    val winner: Boolean = false
)