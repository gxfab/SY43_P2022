package com.example.sy43_projet

import android.widget.ImageView

data class Categories(
    val title : String,
    val icon : ImageView,
)

data class SubCategories (
    val title: String,
    val isSelected: Boolean,
    val icon: ImageView
)
