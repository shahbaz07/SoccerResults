package com.soccer.results.usecase

import com.soccer.results.model.SoccerResult
import com.soccer.results.repository.SoccerResultRepository
import com.soccer.results.repository.SoccerResultsRepository
import com.soccer.results.service.SoccerResultsService
import com.soccer.results.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SoccerResultUseCaseImpl @Inject constructor(private val repository: SoccerResultRepository) : SoccerResultUseCase {

    override suspend fun fetchSoccerResult(uid: Int): Flow<SoccerResult> {
        return repository.fetchSoccerResult(uid)
    }
}