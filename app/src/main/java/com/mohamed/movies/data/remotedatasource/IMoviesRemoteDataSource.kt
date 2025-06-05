package com.mohamed.movies.data.remotedatasource

import com.mohamed.movies.data.dto.MoviesListResponseDto
import com.mohamed.movies.domain.model.MoviesSearchModel

interface IMoviesRemoteDataSource {

    suspend fun getLatestMoviesList(
        moviesSearchModel: MoviesSearchModel
    ): MoviesListResponseDto

}