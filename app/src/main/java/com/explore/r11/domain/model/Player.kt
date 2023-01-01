package com.explore.r11.domain.model

data class Player(
    val name:String,
    val team:String,
    val type:String,
    val salary:String,
    val points:Int = 0,
    val lastMatch:Boolean = false,
    var isPlayerLocked:Boolean = false,
    var isCvcLocked:Boolean = false,
    var isCaptain:Boolean = false,
    var isViceCaptain:Boolean = false
)
