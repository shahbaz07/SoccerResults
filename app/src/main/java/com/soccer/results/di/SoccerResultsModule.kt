package com.soccer.results.di

import com.soccer.results.repository.SoccerResultsRepository
import com.soccer.results.repository.SoccerResultsRepositoryImpl
import com.soccer.results.usecase.SoccerResultsUseCase
import com.soccer.results.usecase.SoccerResultsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SoccerResultsModule {

    @Binds
    abstract fun bindSoccerResultsRepository(repository: SoccerResultsRepositoryImpl) : SoccerResultsRepository

    @Binds
    abstract fun bindSoccerResultsUseCase(useCase: SoccerResultsUseCaseImpl) : SoccerResultsUseCase
}