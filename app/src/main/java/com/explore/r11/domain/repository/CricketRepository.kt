package com.explore.r11.domain.repository

import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player

interface CricketRepository  {
    suspend fun getMatches():List<Match>
    suspend fun getPlayers(matchId: Int):List<Player>
}