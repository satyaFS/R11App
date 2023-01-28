package com.explore.r11.domain.repository

import com.explore.r11.data.local.entities.PlayerEntity
import com.explore.r11.data.local.entities.SelectedPlayersEntity
import com.explore.r11.data.local.entities.TeamEntity
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player

interface CricketRepository  {
    suspend fun getMatches():List<Match>
    suspend fun getPlayers(matchId: Int):List<Player>
    suspend fun saveMatchInfo(
        matchId: Int,
        teamOnePlayers: List<NewPlayer>,
        teamTwoPlayers: List<NewPlayer>,
        teamOne: TeamEntity,
        teamTwo: TeamEntity
    ): List<Int>
    suspend fun saveSelectedPlayers(selectedPlayers:List<Player>)
    suspend fun getSelectedPlayers():List<Player>
    suspend fun clearSelectedPlayers()
}