package com.explore.r11.domain.repository

import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player

interface CricketRepository  {
    suspend fun getMatches():List<Match>
    suspend fun getPlayers(matchId: Int):List<Player>
    suspend fun saveMatchInfo(
        teamOnePlayers: List<NewPlayer>,
        teamTwoPlayers: List<NewPlayer>,
        teamOneName: String,
        teamTwoName:String
    )

}