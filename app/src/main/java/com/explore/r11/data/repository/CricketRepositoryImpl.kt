package com.explore.r11.data.repository

import com.explore.r11.data.local.CricketLocalDataSource
import com.explore.r11.data.local.entities.MatchEntity
import com.explore.r11.data.local.entities.TeamEntity
import com.explore.r11.data.mapper.toMatchTeamPlayers
import com.explore.r11.data.mapper.toPlayer
import com.explore.r11.data.mapper.toPlayerEntity
import com.explore.r11.data.remote.cricket.CricketRemoteDataSource
import com.explore.r11.domain.model.Match
import com.explore.r11.domain.model.NewPlayer
import com.explore.r11.domain.model.Player
import com.explore.r11.domain.repository.CricketRepository
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CricketRepositoryImpl @Inject constructor(
    private val cricketRemoteDataSource: CricketRemoteDataSource,
    private val cricketLocalDataSource: CricketLocalDataSource
):CricketRepository
{
    override suspend fun getMatches():List<Match>{
        val matches = mutableListOf<Match>()
        return try {
           matches.addAll( cricketRemoteDataSource.getMatches() )
            val matchEntities = cricketLocalDataSource.getMatches()
            matchEntities.forEach{
                val nameMap = cricketLocalDataSource.getTeamNameById(listOf(it.teamOneId, it.teamTwoId))
                val match = Match(it.leagueName,nameMap[it.teamOneId]?:"",nameMap[it.teamTwoId]?:"","","",
                    LocalDateTime.now(),it.matchId )
                matches.add(match)
            }
            println(matches)
            matches
        }
        catch (e: IOException){
            e.printStackTrace()
            emptyList<Match>()
        }
    }

    override suspend fun getPlayers(matchId: Int): List<Player> {
        return try{
            val playersMap = cricketLocalDataSource.getPlayers(matchId)
            if(playersMap.isEmpty()){
                cricketRemoteDataSource.getPlayers(matchId)
            }
            else{
                var players = mutableListOf<Player>()
                playersMap.keys.forEach{teamName->
                    playersMap[teamName]?.map { it.toPlayer(teamName) }?.let { players.addAll(it) }
                }
               players
            }
        }
        catch (e: IOException){
            e.printStackTrace()
            emptyList<Player>()
        }
    }

    override suspend fun saveMatchInfo(
        matchId: Int,
        teamOnePlayers: List<NewPlayer>,
        teamTwoPlayers: List<NewPlayer>,
        teamOne: TeamEntity,
        teamTwo: TeamEntity,
    ):List<Int> {
        val teamOnePlayerKeys = cricketLocalDataSource.insertPlayers(players = teamOnePlayers.map { it.toPlayerEntity() })
        val teamTwoPlayerKeys = cricketLocalDataSource.insertPlayers(teamTwoPlayers.map { it.toPlayerEntity() })
        val teamOneKey = cricketLocalDataSource.insertTeam(teamOne)
        val teamTwoKey = cricketLocalDataSource.insertTeam(teamTwo)
        val matchKey = cricketLocalDataSource.insertMatch(MatchEntity(matchId,teamOneKey.toInt(),teamTwoKey.toInt(),"My League"))
        //magic
        val mtpOne = teamOnePlayerKeys.map { toMatchTeamPlayers(matchKey,teamOneKey,it) }
        cricketLocalDataSource.insertMatchTeamPlayers(mtpOne)
        val mtpTwo = teamTwoPlayerKeys.map { toMatchTeamPlayers(matchKey,teamTwoKey,it) }
        cricketLocalDataSource.insertMatchTeamPlayers(mtpTwo)
        return listOf(matchKey.toInt(),teamOneKey.toInt(),teamTwoKey.toInt())
    }

}