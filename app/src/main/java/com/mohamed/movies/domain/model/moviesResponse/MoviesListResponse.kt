package com.mohamed.movies.domain.model.moviesResponse

data class MoviesListResponse(
    val dates: Dates? = null,
    val page: Int? = null,
    val movieListItems: List<MovieListItem>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)