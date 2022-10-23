package com.explore.r11.data.remote.cricket

import com.explore.r11.data.mapper.toMatch
import com.explore.r11.domain.model.Match
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CricketRemoteDataSource constructor(
    private val cricketApi: CricketApi,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getMatches():List<Match>{
       return withContext(ioDispatcher){
            val matches = cricketApi.getMatches()
            matches.map { it.toMatch() }
        }
    }

}
