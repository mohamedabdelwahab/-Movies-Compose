package com.mohamed.movies.domain.repository

import com.mohamed.movies.domain.model.MoviesSearchModel
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getLatestMoviesList(moviesSearchModel: MoviesSearchModel): Flow<Resource<MoviesListResponse>>
}