package com.mohamed.movies.di

import com.example.domain.util.connection_utils.ConnectionUtils
import com.mohamed.movies.data.remotedatasource.IMoviesRemoteDataSource
import com.mohamed.movies.data.remotedatasource.MoviesRemoteDataSource
import com.mohamed.movies.data.repositories.MoviesRepository
import com.mohamed.movies.domain.repository.IMoviesRepository
import com.mohamed.movies.utils.connection_utils.IConnectionUtils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        moviesRepository: MoviesRepository
    ): IMoviesRepository

    @Binds
    @Singleton
    abstract fun bindMoviesRemoteDataSource(
        moviesRepository: MoviesRemoteDataSource
    ): IMoviesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindConnectionUtils(
        connectionUtils: ConnectionUtils
    ): IConnectionUtils


}