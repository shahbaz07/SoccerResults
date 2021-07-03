package com.soccer.results.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soccer.results.model.SoccerResult
import com.soccer.results.model.SoccerResultState
import com.soccer.results.usecase.SoccerResultUseCase
import com.soccer.results.usecase.SoccerResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SoccerResultDetailViewModel @Inject constructor(private val useCase: SoccerResultUseCase) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _soccerResult = MutableStateFlow<SoccerResultState<SoccerResult>>(
        SoccerResultState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val soccerResult: StateFlow<SoccerResultState<SoccerResult>> = _soccerResult

    fun fetchSoccerResults(uid: Int) {
        viewModelScope.launch {
            useCase.fetchSoccerResult(uid)
                .catch { exception -> // Executes in the consumer's context
                    _soccerResult.value = SoccerResultState.Error(exception)
                }
                .collect {
                    _soccerResult.value = SoccerResultState.Success(it)
                }
        }
    }
}