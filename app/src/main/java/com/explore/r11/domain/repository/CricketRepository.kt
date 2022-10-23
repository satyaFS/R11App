package com.explore.r11.domain.repository

import com.explore.r11.data.remote.cricket.CricketRemoteDataSource
import com.explore.r11.domain.model.Match
import java.io.IOException

class CricketRepository constructor(
    private val cricketRemoteDataSource: CricketRemoteDataSource,
) {
    suspend fun getMatches():List<Match>{
        return try {
            cricketRemoteDataSource.getMatches()
        }
        catch (e:IOException){
            e.printStackTrace()
            emptyList<Match>()
        }
    }
}