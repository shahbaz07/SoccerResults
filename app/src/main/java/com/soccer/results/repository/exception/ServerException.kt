package com.soccer.results.repository.exception

import java.lang.Exception
import java.net.HttpURLConnection

data class ServerException(val code: Int = HttpURLConnection.HTTP_INTERNAL_ERROR) : Exception("Something went wrong please try again later")