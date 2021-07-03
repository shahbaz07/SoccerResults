package com.soccer.results.di

import com.soccer.results.repository.SoccerResultRepository
import com.soccer.results.repository.SoccerResultRepositoryImpl
import com.soccer.results.repository.SoccerResultsRepository
import com.soccer.results.repository.SoccerResultsRepositoryImpl
import com.soccer.results.usecase.SoccerResultUseCase
import com.soccer.results.usecase.SoccerResultUseCaseImpl
import com.soccer.results.usecase.SoccerResultsUseCase
import com.soccer.results.usecase.SoccerResultsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SoccerResultModule {

    @Binds
    abstract fun bindSoccerResultRepository(repository: SoccerResultRepositoryImpl) : SoccerResultRepository

    @Binds
    abstract fun bindSoccerResultUseCase(useCase: SoccerResultUseCaseImpl) : SoccerResultUseCase
}