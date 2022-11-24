package com.explore.r11.data.remote.cricket

import com.explore.r11.data.mapper.toMatch
import com.explore.r11.data.mapper.toPlayer
import com.explore.r11.di.IoDispatcher
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CricketRemoteDataSource @Inject constructor(
    private val cricketApi: CricketApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getMatches():List<Match>{
        val matches = cricketApi.getMatches()
        return  matches.map { it.toMatch() }
    }
    //code not working with dispatcher, as api call is already thread safe
    suspend fun getPlayers(matchId:Int):List<Player>{
        val players = cricketApi.getPlayers(matchId)
        return players.map { it.toPlayer() };
    }
}
