package com.mohamed.movies.domain.repository

import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getLatestMoviesList(
        language: String,
        page: Int
    ): Flow<Resource<MoviesListResponse>>

    suspend fun getSearchSuggestions(
        query: String,
        language: String
    ): Flow<Resource<ArrayList<MovieListItem>>>
}