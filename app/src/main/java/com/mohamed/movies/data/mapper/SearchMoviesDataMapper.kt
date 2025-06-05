package com.mohamed.movies.data.mapper

import com.mohamed.movies.data.dto.moviesSuggestion.SearchResultMovie
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem

fun SearchResultMovie.toMovieListItem(): MovieListItem {
    return MovieListItem(
        id = this.id,
        original_title = this.originalTitle,
        title = this.title,
        poster_path = this.posterPath,
        overview = this.overview,
        release_date = this.releaseDate,
        vote_average = this.voteAverage
    )
}
