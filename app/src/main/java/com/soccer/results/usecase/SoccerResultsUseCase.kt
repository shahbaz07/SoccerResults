package com.soccer.results.usecase

import com.soccer.results.model.SoccerResult
import kotlinx.coroutines.flow.Flow

interface SoccerResultsUseCase {

    suspend fun fetchSoccerResults(): Flow<List<SoccerResult>>
}