package com.explore.r11.domain.model

data class Player(
    val playerId:Int = 0,
    val playerName:String,
    val teamName:String,
    val playerType:String,
    val playerSalary:String,
    val points:Int = 0,
    val lastMatch:Boolean = false,
    var isPlayerLocked:Boolean = false,
    var isCvcLocked:Boolean = false,
    var isCaptain:Boolean = false,
    var isViceCaptain:Boolean = false
)
