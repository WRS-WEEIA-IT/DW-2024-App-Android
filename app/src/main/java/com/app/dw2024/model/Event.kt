package com.app.dw2024.model

import com.google.firebase.Timestamp

class Event(
    val room: String = "",
    val imageSrc: String = "",
    val partner: String = "",
    val timeEnd: Timestamp = Timestamp.now(),
    val timeStart: Timestamp = Timestamp.now(),
    val title: String = "",
    var type: String = ""
)