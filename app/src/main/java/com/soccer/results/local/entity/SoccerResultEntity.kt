package com.soccer.results.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SoccerResultEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Team_A") val teamOneName: String,
    @ColumnInfo(name = "Team_B") val teamTwoName: String,
    @ColumnInfo(name = "Score") val score: String,
    @ColumnInfo(name = "link_A") val teamOneLogo: String,
    @ColumnInfo(name = "link_B") val teamTwoLogo: String,
    @ColumnInfo(name = "Date") val dateTime: String
)