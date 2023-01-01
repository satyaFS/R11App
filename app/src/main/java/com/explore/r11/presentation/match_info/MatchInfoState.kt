package com.explore.r11.presentation.match_info

import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player

data class MatchInfoState(
    val matchId:String = "",
    val league:String ="",
    val teamOne:String ="",
    val teamTwo:String ="",
    val selectedPlayers:List<Player> = emptyList(),
    val removedPlayers:List<Player> = emptyList(),
    val isLoading:Boolean = false,
)