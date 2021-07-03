package com.soccer.results.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.soccer.results.local.entity.SoccerResultEntity

@Dao
interface SoccerResultDao {
    @Query("SELECT * FROM SoccerResults")
    fun getAll(): List<SoccerResultEntity>

    @Query("SELECT * FROM SoccerResults WHERE uid = (:uid)")
    fun loadAllByIds(uid: Int): SoccerResultEntity

    @Insert
    fun insertAll(vararg results: SoccerResultEntity)

    @Delete
    fun delete(result: SoccerResultEntity)

    @Delete
    fun deleteAll(vararg result: SoccerResultEntity)
}