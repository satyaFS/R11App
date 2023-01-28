package com.explore.r11.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.explore.r11.data.local.entities.*

@Database(
    entities = [MatchEntity::class, TeamEntity::class, PlayerEntity::class, MatchTeamPlayersEntity::class,SelectedPlayersEntity::class],
    version = 1
)
abstract class CricketDatabase:RoomDatabase() {
    abstract val cricketDao:CricketDao
}