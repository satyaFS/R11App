package com.explore.r11.data.mapper

import androidx.core.text.isDigitsOnly
import com.explore.r11.data.local.entities.MatchTeamPlayersEntity
import com.explore.r11.data.local.entities.PlayerEntity
import com.explore.r11.data.local.entities.SelectedPlayersEntity
import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.explore.r11.data.remote.cricket.dto.PlayerDto
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.NewPlayer
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
        playerName = name?:"",
        teamName = team?:"",
        playerType = type?:"",
        playerSalary = salary?:"",
        points = if(points?.isDigitsOnly() == true) points.toInt() else 0
    )
}
fun NewPlayer.toPlayerEntity():PlayerEntity{
    return PlayerEntity(
        playerName = name,
        playerType = type,
        playerSalary = salary,
        playerId = id
    )
}

fun toMatchTeamPlayers(matchId:Long,teamId:Long, playerId:Long):MatchTeamPlayersEntity{
    return MatchTeamPlayersEntity(
        matchId = matchId.toInt(),
        teamId = teamId.toInt(),
        playerId = playerId.toInt()
    )
}

fun PlayerEntity.toPlayer(teamName:String):Player{
    return Player(
        playerId = playerId,
        teamName = teamName,
        playerName = playerName,
        playerType = playerType,
        playerSalary = playerSalary.toString()
    )
}

fun Player.toNewPlayer():NewPlayer{
    return NewPlayer(
        id = playerId,
        name = playerName,
        type = playerType,
        salary = playerSalary.toDouble(),
        team = teamName
    )
}

fun Player.toSelectedPlayerEntity():SelectedPlayersEntity{
    return SelectedPlayersEntity(
        playerId = playerId,
        playerSalary = playerSalary,
        teamName = teamName,
        playerType = playerType,
        playerName = playerName,
        isPlayerLocked = isPlayerLocked,
        isCvcLocked = isCvcLocked,
        isCaptain = isCaptain,
        isViceCaptain = isViceCaptain
    )
}


















