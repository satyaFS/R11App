package com.explore.r11.data.local

import androidx.room.*
import com.explore.r11.data.local.entities.*

@Dao
interface CricketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(
        matchEntities:MatchEntity
    ):Long

    @Query("SELECT * FROM MatchEntity")
    suspend fun getMatches():List<MatchEntity>

    //team
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(
        teamEntity: TeamEntity
    ):Long

    @MapInfo(keyColumn = "teamId", valueColumn = "teamName")
    @Query("SELECT TeamEntity.teamId, teamName FROM TeamEntity WHERE teamId in (:Ids)")
    suspend fun getTeamNameById(Ids:List<Int>):Map<Int, String>

    //players
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(players:List<PlayerEntity>):List<Long>
    @MapInfo(keyColumn = "teamName")
    @Query("""SELECT TeamEntity.teamName,PlayerEntity.* FROM TeamEntity 
        INNER JOIN MatchTeamPlayersEntity on TeamEntity.teamID = MatchTeamPlayersEntity.teamID
        AND MatchTeamPlayersEntity.matchID = :matchId
        INNER JOIN PlayerEntity ON PlayerEntity.playerId = MatchTeamPlayersEntity.playerId
    """)
    suspend fun getPlayers(matchId:Int):Map<String,List<PlayerEntity>>

    //MatchTeamPlayers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchTeamPlayers(matchTeamPlayers: List<MatchTeamPlayersEntity>):List<Long>



}



















