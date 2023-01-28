package com.explore.r11.data.local

import com.explore.r11.data.local.entities.*
import com.explore.r11.di.IoDispatcher
import com.explore.r11.domain.model.Player
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CricketLocalDataSource @Inject constructor(
    private val cricketDB:CricketDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val cricketDao = cricketDB.cricketDao
    suspend fun getMatches():List<MatchEntity>{
        return withContext(ioDispatcher){
            cricketDao.getMatches()
        }
    }
    suspend fun getTeamNameById(ids:List<Int>):Map<Int,String>{
        return withContext(ioDispatcher){
            cricketDao.getTeamNameById(ids)
        }
    }
    suspend fun insertPlayers(players:List<PlayerEntity>):List<Long>{
        return withContext(ioDispatcher){
            cricketDao.insertPlayers(players)
        }
    }
    suspend fun insertTeam(team:TeamEntity):Long{
        return withContext(ioDispatcher){
            cricketDao.insertTeam(team)
        }
    }
    suspend fun insertMatch(match:MatchEntity):Long{
        return withContext(ioDispatcher){
            cricketDao.insertMatch(match)
        }
    }
    suspend fun insertMatchTeamPlayers(matchTeamPlayers: List<MatchTeamPlayersEntity>):List<Long>{
        return withContext(ioDispatcher){
            cricketDao.insertMatchTeamPlayers(matchTeamPlayers)
        }
    }
    suspend fun getPlayers(matchId:Int): Map<String, List<PlayerEntity>> {
        return withContext(ioDispatcher){
            cricketDao.getPlayers(matchId)
        }
    }
    suspend fun saveSelectedPlayers(selectedPlayers:List<SelectedPlayersEntity>){
        withContext(ioDispatcher){
            cricketDao.insertSelectedPlayers(selectedPlayers)
        }
    }
    suspend fun getSelectedPlayers():List<Player>{
        return withContext(ioDispatcher){
            cricketDao.getSelectedPlayers()
        }
    }
    suspend fun clearSelectedPlayers(){
        withContext(ioDispatcher){
            cricketDao.clearSelectedPlayers()
        }
    }
}