package com.explore.r11.domain.model

import java.time.LocalDateTime

data class Match(
    val league: String,
    val teamOne: String,
    val teamTwo:String,
    val teamOneSC:String,
    val teamTwoSC:String,
    val startTime:LocalDateTime,
    val matchID:Int
)
