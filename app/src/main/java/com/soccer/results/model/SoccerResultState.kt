package com.soccer.results.model

sealed class SoccerResultState {
    data class Success(val results: List<SoccerResult>): SoccerResultState()
    data class Error(val exception: Throwable): SoccerResultState()
}