package com.soccer.results.ui

import com.soccer.results.model.SoccerResult

interface SoccerResultItemClickListener {

    fun onItemClick(result: SoccerResult)
}