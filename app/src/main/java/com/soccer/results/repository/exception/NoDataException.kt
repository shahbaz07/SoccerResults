package com.soccer.results.repository.exception

import java.lang.Exception
import java.net.HttpURLConnection

data class NoDataException(val code: Int = HttpURLConnection.HTTP_NO_CONTENT) : Exception("No data found.")