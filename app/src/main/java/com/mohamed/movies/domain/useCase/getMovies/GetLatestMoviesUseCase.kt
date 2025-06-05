package com.mohamed.movies.domain.useCase.getMovies

import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import com.mohamed.movies.domain.repository.IMoviesRepository
import com.mohamed.movies.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestMoviesUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
    suspend operator fun invoke(
        language: String = Constants.Defaults.DEFAULT_LANGUAGE,
        page: Int
    ): Flow<Resource<MoviesListResponse>> {
        return iMoviesRepository.getLatestMoviesList(language = language, page = page)
    }
}