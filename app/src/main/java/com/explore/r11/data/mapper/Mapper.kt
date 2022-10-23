package com.explore.r11.data.mapper

import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.explore.r11.domain.model.Match
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun MatchDto.toMatch():Match{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(startTime, formatter)
    return Match(
        league  = league?:"",
        teamOne = teamOne?:"",
        teamTwo = teamTwo?:"",
        teamOneSC = teamOneSC?:"",
        teamTwoSC = teamTwoSC?:"",
        startTime = localDateTime,
        matchID = matchID?.toInt()?:0
    )

}