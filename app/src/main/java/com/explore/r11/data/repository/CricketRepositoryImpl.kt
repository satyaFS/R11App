package com.explore.r11.data.repository

import com.explore.r11.data.remote.cricket.CricketRemoteDataSource
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.repository.CricketRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CricketRepositoryImpl @Inject constructor(
    private val cricketRemoteDataSource: CricketRemoteDataSource,
):CricketRepository
{
    override suspend fun getMatches():List<Match>{
        return try {
            cricketRemoteDataSource.getMatches()
        }
        catch (e: IOException){
            e.printStackTrace()
            emptyList<Match>()
        }
    }

    override suspend fun getPlayers(matchId: Int): List<Player> {
        return try{
            cricketRemoteDataSource.getPlayers(matchId)
        }
        catch (e: IOException){
            e.printStackTrace()
            emptyList<Player>()
        }
    }
}