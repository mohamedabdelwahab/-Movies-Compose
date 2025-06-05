package com.mohamed.movies.data.remotedatasource

import com.mohamed.movies.data.dto.latestMovies.MoviesListResponseDto
import com.mohamed.movies.data.dto.movieDetails.MovieDetailsResponseDto
import com.mohamed.movies.data.dto.moviesSuggestion.MovieSuggestionsDto
import com.mohamed.movies.data.retrofit.MovieAPIInterface
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val movieApiInterface: MovieAPIInterface
) : IMoviesRemoteDataSource {

    override suspend fun getLatestMoviesList(
        language: String, page: Int
    ): MoviesListResponseDto {
        return movieApiInterface.getLatestMoviesList(
            page, language
        )
    }

    override suspend fun getSearchSuggestions(
        query: String, language: String
    ): MovieSuggestionsDto {
        return movieApiInterface.getSearchSuggestions(
            query = query, language = language
        )
    }

    override suspend fun getMovieDetailsById(
        movieId: Int, language: String
    ): MovieDetailsResponseDto {
        return movieApiInterface.getMovieDetailsById(
            movieId = movieId, language = language
        )
    }

}