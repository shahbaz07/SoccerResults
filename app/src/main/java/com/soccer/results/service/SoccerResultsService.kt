package com.soccer.results.service

import com.soccer.results.model.SoccerResult
import retrofit2.Response
import retrofit2.http.GET

private const val FIRST_API: String = "/v3/23745f3a-5eaa-43cf-ab46-737eb273596b"
private const val SECOND_API: String = "/v3/bc1ce3b7-6ad2-4fef-af6c-08f8865b210e"

interface SoccerResultsService {

    @GET(FIRST_API)
    suspend fun fetchSoccerResults(): Response<List<SoccerResult>>

    @GET(SECOND_API)
    suspend fun fetchMoreSoccerResults(): Response<List<SoccerResult>>
}