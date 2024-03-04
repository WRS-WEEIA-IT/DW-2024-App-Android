package com.app.dw2024.home

import androidx.annotation.DrawableRes
import com.app.dw2024.R
import com.app.dw2024.model.Event
import com.app.dw2024.model.Task

data class HomeState(
    val events : List<Event> = listOf(),
    val tasks : List<Task> = listOf(),
    val pagerImages: List<Int> = listOf(
        R.drawable.home_background_image_1,
        R.drawable.home_background_image_2,
        R.drawable.home_background_image_3,
        R.drawable.home_background_image_4,
        R.drawable.home_background_image_5,
        R.drawable.home_background_image_6,
        R.drawable.home_background_image_1,
    )
)
