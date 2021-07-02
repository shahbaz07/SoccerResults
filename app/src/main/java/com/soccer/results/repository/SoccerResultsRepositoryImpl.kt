package com.soccer.results.repository

import com.soccer.results.model.SoccerResult
import com.soccer.results.service.SoccerResultsService
import com.soccer.results.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class SoccerResultsRepositoryImpl @Inject constructor(
    private val service: SoccerResultsService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SoccerResultsRepository {

    override suspend fun fetchSoccerResults(): Flow<List<SoccerResult>> {
        return getSoccerResultsFlow().zip(getMoreSoccerResultsFlow()) { first, second ->
            first + second
        }
        .flowOn(dispatcher)
    }

    private fun getSoccerResultsFlow() : Flow<List<SoccerResult>> {
        return flow {
            val response = safeApiCall {
                service.fetchSoccerResults()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: kotlin.run {
                    throw Exception()
                }
            } else {
                throw Exception()
            }
        }
    }

    private fun getMoreSoccerResultsFlow() : Flow<List<SoccerResult>> {
        return flow {
            val response = safeApiCall {
                service.fetchMoreSoccerResults()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: kotlin.run {
                    throw Exception()
                }
            } else {
                throw Exception()
            }
        }
    }
}