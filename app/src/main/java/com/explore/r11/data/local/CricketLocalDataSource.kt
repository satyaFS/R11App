package com.explore.r11.data.local

import com.explore.r11.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CricketLocalDataSource @Inject constructor(
    private val cricketDB:CricketDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val cricketDao = cricketDB.cricketDao

    suspend fun getMatches(){
        return withContext(ioDispatcher){
            cricketDao.getMatches()
        }
    }

    suspend fun getTeamNameById(ids:List<Int>){
        return withContext(ioDispatcher){
            cricketDao.getTeamNameById(ids)
        }
    }

}