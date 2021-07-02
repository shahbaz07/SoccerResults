package com.soccer.results.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soccer.results.model.SoccerResultState
import com.soccer.results.usecase.SoccerResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SoccerResultsViewModel @Inject constructor(private val useCase: SoccerResultsUseCase) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _soccerResults = MutableStateFlow<SoccerResultState>(SoccerResultState.Success(
        emptyList()))
    // The UI collects from this StateFlow to get its state updates
    val soccerResults: StateFlow<SoccerResultState> = _soccerResults

    fun fetchSoccerResults() {
        viewModelScope.launch {
            useCase.fetchSoccerResults()
                .catch { exception -> // Executes in the consumer's context
                    _soccerResults.value = SoccerResultState.Error(exception)
                }
                .collect {
                    _soccerResults.value = SoccerResultState.Success(it)
                    Timber.e("${it.size}")
                }
        }
    }
}