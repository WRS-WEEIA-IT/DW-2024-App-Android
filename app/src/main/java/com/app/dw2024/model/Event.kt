package com.app.dw2024.model

import com.google.firebase.Timestamp
import java.time.LocalDateTime

class Event(
    val hall: String = "",
    val imageSource: String = "",
    val partner: String = "",
    val timeEnd: Timestamp = Timestamp.now(),
    val timeStart: Timestamp = Timestamp.now(),
    val title: String = "",
    var type: String = ""
)