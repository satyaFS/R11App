package com.explore.r11.presentation.newTeam

import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player

data class NewTeamState(
    val teamOnePlayers:List<NewPlayer> = emptyList(),
    val teamTwoPlayers:List<NewPlayer> = emptyList(),
    val teamOneCount:Int = 0,
    val teamTwoCount:Int = 0,
    val playerName:String = "",
    val playerSalary:String = "",
    val teamOneName:String = "Team One",
    val teamTwoName:String = "Team Two",
    val isLoading:Boolean = false
)
