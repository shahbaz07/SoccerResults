package com.soccer.results.repository

import com.soccer.results.model.SoccerResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SoccerResultRepository {

    suspend fun fetchSoccerResult(uid: Int): Flow<SoccerResult>
}