package com.soccer.results.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.soccer.results.local.entity.SoccerResultEntity

@Dao
interface SoccerResultDao {
    @Query("SELECT * FROM SoccerResultEntity")
    fun getAll(): List<SoccerResultEntity>

    @Query("SELECT * FROM SoccerResultEntity WHERE uid = (:uid)")
    fun loadById(uid: Int): SoccerResultEntity

    @Insert
    fun insertAll(results: List<SoccerResultEntity>)

    @Delete
    fun delete(result: SoccerResultEntity)

    @Delete
    fun deleteAll(results: List<SoccerResultEntity>)
}