package com.soccer.results.model

import com.google.gson.annotations.SerializedName

data class SoccerResult(
    @SerializedName("Team_A") val teamOneName: String,
    @SerializedName("Team_B") val teamTwoName: String,
    @SerializedName("Score") val score: String,
    @SerializedName("link_A") val teamOneLogo: String,
    @SerializedName("link_B") val teamTwoLogo: String,
    @SerializedName("Date") val dateTime: String
)