package com.explore.r11.data.remote.cricket

import com.explore.r11.data.remote.cricket.dto.MatchDto

interface CricketApi {

    suspend fun getMatches():List<MatchDto>

}