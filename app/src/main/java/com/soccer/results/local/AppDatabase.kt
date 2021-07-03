package com.soccer.results.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soccer.results.local.entity.SoccerResultEntity

@Database(entities = [SoccerResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun soccerResultDao(): SoccerResultDao
}