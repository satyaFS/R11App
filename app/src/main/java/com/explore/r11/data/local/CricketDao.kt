package com.explore.r11.data.local

import androidx.room.*
import com.explore.r11.data.local.entities.*
import com.explore.r11.domain.model.Player

@Dao
interface CricketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(
        matchEntities:MatchEntity
    ):Long

    @Query("SELECT * FROM MatchEntity")
    suspend fun getMatches():List<MatchEntity>

    //teamName
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

    //SelectedPlayers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedPlayers(selectedPlayerEntities:List<SelectedPlayersEntity>)

    @Query("SELECT 0 as points,'false' as lastMatch, * FROM SelectedPlayersEntity ")
    suspend fun getSelectedPlayers():List<Player>
    @Query("DELETE FROM SelectedPlayersEntity")
    suspend fun clearSelectedPlayers()

    //Generated Teams
    @MapInfo(keyColumn = "teamNo")
    @Query("""SELECT  0 as points,'false' as lastMatch,'false' as isPlayerLocked, 'false' as isCvcLocked, 
            t.teamName, gt.teamNo, gt.isCaptain,gt.isViceCaptain,p.* FROM GeneratedTeamsEntity gt
            JOIN PlayerEntity p on gt.playerId = p.playerId and 
            matchId=:matchId and setNo = :setNo
            JOIN TeamEntity t on t.teamId = gt.teamId """)
    suspend fun getGeneratedTeams(matchId:Int, setNo:Int):Map<Int,List<Player>>
}



















