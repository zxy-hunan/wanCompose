package com.zxy_hunan.compose.entity

data class TabTitle(
    val id: Int,
    val text: String,
    var cachePosition: Int = 0,
    var selected: Boolean = false
)