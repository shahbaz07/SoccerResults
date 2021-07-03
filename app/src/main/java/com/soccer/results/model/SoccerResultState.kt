package com.soccer.results.model

sealed class SoccerResultState<out T : Any> {
    object Loading: SoccerResultState<Nothing>()
    data class Success<out T : Any>(val results: T): SoccerResultState<T>()
    data class Error(val exception: Throwable): SoccerResultState<Nothing>()
}