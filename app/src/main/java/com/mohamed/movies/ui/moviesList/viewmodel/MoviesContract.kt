package com.mohamed.movies.ui.moviesList.viewmodel

import com.mohamed.movies.domain.model.Failure
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.mvi_base.Event
import com.mohamed.movies.ui.mvi_base.OneTimeAction
import com.mohamed.movies.ui.mvi_base.UIState

data class MoviesViewState(
    val searchText: String = "",
    val canPaginate: Boolean = true,
    val page: Int = 0,
    val progressTypes: ProgressTypes = ProgressTypes.NONE,
    val moviesList: ArrayList<MovieListItem>? = null,
    val searchSuggestions: ArrayList<MovieListItem>? = null,
    val moviesErrorModel: Failure? = null,
    val showSuggestions: Boolean = false,
) : UIState

sealed class MoviesEvents : Event {
    data object LoadMovies : MoviesEvents()
    data object OnPullToRefresh : MoviesEvents()
    data object OnClickRetry : MoviesEvents()
    data object OnLoadMoreMovies : MoviesEvents()
    data class OnSearchQueryChanged(val newText: String) : MoviesEvents()
    data class OnClickMovies(val movie: MovieListItem) :
        MoviesEvents()

    data class OnSuggestionSelected(val movie: MovieListItem) :
        MoviesEvents()

    data class OnSearchSubmit(val newText: String) :
        MoviesEvents()

    data object OnDismissSearch :
        MoviesEvents()
}

sealed class MoviesOneTimeAction : OneTimeAction {
    data class NavigateToMovieDetails(val movieListItem: MovieListItem) :
        MoviesOneTimeAction()
}
