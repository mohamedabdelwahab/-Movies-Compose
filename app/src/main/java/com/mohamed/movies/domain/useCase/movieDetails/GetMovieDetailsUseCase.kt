package com.mohamed.movies.domain.useCase.movieDetails

import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.movieDetails.MovieDetailsReposeModel
import com.mohamed.movies.domain.repository.IMoviesRepository
import com.mohamed.movies.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val iMoviesRepository: IMoviesRepository) {
    suspend operator fun invoke(
        movieId: Int, language: String = Constants.Defaults.DEFAULT_LANGUAGE
    ): Flow<Resource<MovieDetailsReposeModel>> {
        return iMoviesRepository.getMovieDetailsById(movieId = movieId, language = language)
    }
}