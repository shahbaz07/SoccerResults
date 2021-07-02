package com.soccer.results.repository

import com.soccer.results.model.SoccerResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SoccerResultsRepository {

    suspend fun fetchSoccerResults(): Flow<List<SoccerResult>>
}