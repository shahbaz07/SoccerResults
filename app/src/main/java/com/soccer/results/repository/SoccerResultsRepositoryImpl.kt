package com.soccer.results.repository

import com.soccer.results.local.SoccerResultDao
import com.soccer.results.local.entity.SoccerResultEntity
import com.soccer.results.model.SoccerResult
import com.soccer.results.repository.exception.NoDataException
import com.soccer.results.repository.exception.ServerException
import com.soccer.results.service.SoccerResultsService
import com.soccer.results.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class SoccerResultsRepositoryImpl @Inject constructor(
    private val service: SoccerResultsService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dao: SoccerResultDao
) : SoccerResultsRepository {

    override suspend fun fetchSoccerResults(): Flow<List<SoccerResult>> {
        return getSoccerResultsFlow().zip(getMoreSoccerResultsFlow()) { first, second ->
            first + second
        }
        .onStart {
            // Reading local data and updating ui
            val localData = getLocalData()
            if (localData.isNotEmpty()) {
                emit(localData)
            }
        }
        .onEach {
            // Removing old data and saving new data to local database
            updateLocalData(it)
        }
        .flowOn(dispatcher)
    }

    private fun getLocalData(): List<SoccerResult> {
        val localData = dao.getAll()
        return if (localData.isNotEmpty()) {
            localData.map {
                    SoccerResult(
                        it.teamOneName, it.teamTwoName,
                        it.score, it.teamOneLogo, it.teamTwoLogo, it.dateTime
                    )
            }
        } else {
            emptyList()
        }
    }

    private fun updateLocalData(results: List<SoccerResult>) {
        val entities = results.map {
            SoccerResultEntity(
                it.hashCode(), it.teamOneName, it.teamTwoName,
                it.score, it.teamOneLogo, it.teamTwoLogo, it.dateTime
            )
        }
        dao.deleteAll()
        dao.insertAll(entities)
    }

    private fun getSoccerResultsFlow() : Flow<List<SoccerResult>> {
        return flow {
            // Fetching data from API
            val response = safeApiCall {
                service.fetchSoccerResults()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: kotlin.run {
                    throw NoDataException()
                }
            } else {
                throw ServerException()
            }
        }
    }

    private fun getMoreSoccerResultsFlow() : Flow<List<SoccerResult>> {
        return flow {
            // Fetching more data from API
            val response = safeApiCall {
                service.fetchMoreSoccerResults()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                } ?: kotlin.run {
                    throw NoDataException()
                }
            } else {
                throw ServerException()
            }
        }
    }
}