package com.explore.r11.data.remote.cricket

import com.explore.r11.data.remote.cricket.dto.MatchDto
import com.explore.r11.data.remote.cricket.dto.PlayerDto

interface CricketApi {

    suspend fun getMatches():List<MatchDto>
    suspend fun getPlayers(matchId:Int):List<PlayerDto>

}