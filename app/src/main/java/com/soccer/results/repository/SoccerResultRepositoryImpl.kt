package com.soccer.results.repository

import com.soccer.results.local.SoccerResultDao
import com.soccer.results.local.entity.SoccerResultEntity
import com.soccer.results.model.SoccerResult
import com.soccer.results.service.SoccerResultsService
import com.soccer.results.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class SoccerResultRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dao: SoccerResultDao
) : SoccerResultRepository {

    override suspend fun fetchSoccerResult(uid: Int): Flow<SoccerResult> {
        return flow {
            val response = dao.loadById(uid)
            emit(SoccerResult(response.teamOneName, response.teamTwoName, response.score,
                response.teamOneLogo, response.teamTwoLogo, response.dateTime))
        }
        .flowOn(dispatcher)
    }
}