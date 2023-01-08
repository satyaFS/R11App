package com.explore.r11.presentation.newTeam

import com.explore.r11.domain.model.Player

data class NewTeamState(
    val teamOnePlayers:List<Player> = emptyList(),
    val teamTwoPlayers:List<Player> = emptyList(),
    val playerName:String = "",
    val playerSalary:String = "",
    val teamOneName:String = "teamOne",
    val teamTwoName:String = "teamTwo",
    val isLoading:Boolean = false
)
