package com.explore.r11.data.mapper

import androidx.core.text.isDigitsOnly
import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.explore.r11.data.remote.cricket.dto.PlayerDto
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun MatchDto.toMatch():Match{
    val pattern = "yyyy-MM-dd HH:mm"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(startTime, formatter)
    return Match(
        league  = league?:"",
        teamOne = teamOne?:"",
        teamTwo = teamTwo?:"",
        teamOneSC = teamOneSC?:"",
        teamTwoSC = teamTwoSC?:"",
        startTime = localDateTime,
        matchID = if(matchID?.isDigitsOnly() == true) matchID.toInt() else 0
    )
}

fun PlayerDto.toPlayer():Player{
    return Player(
        name = name?:"",
        team = team?:"",
        type = type?:"",
        salary = salary?:"",
        points = if(points?.isDigitsOnly() == true) points.toInt() else 0
    )
}