package com.example.grid_topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topics (
    @StringRes val stringResourceId: Int,
    val availableCourses:Int,
    @DrawableRes val imageResourceId: Int
)