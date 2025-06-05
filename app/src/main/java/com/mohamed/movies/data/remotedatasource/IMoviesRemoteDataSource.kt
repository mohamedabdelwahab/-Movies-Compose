package com.mohamed.movies.data.remotedatasource

import com.mohamed.movies.data.dto.latestMovies.MoviesListResponseDto
import com.mohamed.movies.data.dto.moviesSuggestion.MovieSuggestionsDto

interface IMoviesRemoteDataSource {

    suspend fun getLatestMoviesList(
        language: String,
        page: Int
    ): MoviesListResponseDto

    suspend fun getSearchSuggestions(query: String, language: String): MovieSuggestionsDto
}