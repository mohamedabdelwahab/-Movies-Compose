package com.mohamed.movies.domain.domain.getMovies

import com.mohamed.movies.domain.model.MoviesSearchModel
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import com.mohamed.movies.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
    suspend operator fun invoke(moviesSearchModel: MoviesSearchModel): Flow<Resource<MoviesListResponse>> {
        return iMoviesRepository.getLatestMoviesList(moviesSearchModel)
    }
}