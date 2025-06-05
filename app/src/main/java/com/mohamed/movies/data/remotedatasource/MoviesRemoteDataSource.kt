package com.mohamed.movies.data.remotedatasource

import com.mohamed.movies.data.dto.MoviesListResponseDto
import com.mohamed.movies.data.remote.MovieAPIInterface
import com.mohamed.movies.domain.model.MoviesSearchModel
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val movieApiInterface: MovieAPIInterface
) : IMoviesRemoteDataSource {

    override suspend fun getLatestMoviesList(
        moviesSearchModel: MoviesSearchModel
    ): MoviesListResponseDto {
        return movieApiInterface.getLatestMoviesList(
            moviesSearchModel.page,
            moviesSearchModel.language
        )
    }

}