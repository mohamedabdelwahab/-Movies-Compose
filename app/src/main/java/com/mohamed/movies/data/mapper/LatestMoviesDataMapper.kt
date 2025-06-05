package com.mohamed.movies.data.mapper


import com.mohamed.movies.data.dto.latestMovies.MoviesListResponseDto
import com.mohamed.movies.domain.model.moviesResponse.MoviesListResponse
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem

fun MoviesListResponseDto.toMoviesDomainModel(): MoviesListResponse {
    return MoviesListResponse(
        page = this.page,
        total_pages = this.totalPages,
        total_results = this.totalResults,
        movieListItems = this.movieItems?.map {
            MovieListItem(
                id = it?.id,
                original_title = it?.originalTitle,
                title = it?.title,
                poster_path = it?.posterPath,
                overview = it?.overview,
                release_date = it?.releaseDate,
                vote_average = it?.voteAverage
            )
        })
}
