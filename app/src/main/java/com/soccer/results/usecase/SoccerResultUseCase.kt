package com.soccer.results.usecase

import com.soccer.results.model.SoccerResult
import kotlinx.coroutines.flow.Flow

interface SoccerResultUseCase {

    suspend fun fetchSoccerResult(uid: Int): Flow<SoccerResult>
}