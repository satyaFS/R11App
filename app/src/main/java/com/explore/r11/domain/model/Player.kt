package com.explore.r11.domain.model

data class Player(
    val name:String,
    val team:String,
    val type:String,
    val salary:String,
    val points:Int = 0,
    val lastMatch:Boolean = false,
    val isPlayerLocked:Boolean = false,
    val isCvcLocked:Boolean = false,
    val isCaptain:Boolean = false,
    val isViceCaptain:Boolean = false
)
