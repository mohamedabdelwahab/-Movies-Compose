package com.mohamed.movies.ui.moviesList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.moviesList.components.ErrorMessage
import com.mohamed.movies.ui.moviesList.components.MoviesDefaultProgress
import com.mohamed.movies.ui.moviesList.components.MoviesList
import com.mohamed.movies.ui.moviesList.components.SearchBarWithPopupSuggestions
import com.mohamed.movies.ui.moviesList.viewmodel.MoviesEvents
import com.mohamed.movies.ui.moviesList.viewmodel.MoviesOneTimeAction
import com.mohamed.movies.ui.moviesList.viewmodel.MoviesViewState


@ExperimentalMaterial3Api
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesOneTimeAction: MoviesOneTimeAction?,
    moviesViewState: MoviesViewState,
    onEvent: (MoviesEvents) -> Unit = {},
    onMovieClick: (MovieListItem) -> Unit
) {

    moviesOneTimeAction?.let {
        HandleAction(it, onMovieClick)
    }

    MoviesContent(
        modifier = modifier,
        moviesViewState = moviesViewState,
        onEvent = { event -> onEvent(event) },
    )
}

@Composable
private fun MoviesContent(
    modifier: Modifier,
    moviesViewState: MoviesViewState,
    onEvent: (MoviesEvents) -> Unit,
) {
    Column(modifier = modifier) {

        SearchBarWithPopupSuggestions(
            query = moviesViewState.searchText,
            onQueryChanged = { onEvent(MoviesEvents.OnSearchQueryChanged(it)) },
            suggestions = moviesViewState.searchSuggestions ?: arrayListOf(),
            onMovieSelected = { onEvent(MoviesEvents.OnClickMovies(it)) },
            onDismiss = { onEvent(MoviesEvents.OnDismissSearch) },
            onSearchSubmit = {},
            showSuggestions = moviesViewState.showSuggestions
        )

        MoviesPullToRefresh(
            moviesViewState = moviesViewState,
            onRefresh = { onEvent(MoviesEvents.OnPullToRefresh) },
            onMovieClick = { onEvent(MoviesEvents.OnClickMovies(it)) },
            onClickRetry = { onEvent(MoviesEvents.OnClickRetry) },
            onBottomReached = { onEvent(MoviesEvents.OnLoadMoreMovies) }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesPullToRefresh(
    moviesViewState: MoviesViewState,
    onRefresh: () -> Unit,
    onMovieClick: (MovieListItem) -> Unit,
    onBottomReached: () -> Unit,
    onClickRetry: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = moviesViewState.progressTypes == ProgressTypes.PULL_TO_REFRESH_PROGRESS,
        onRefresh = onRefresh
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {

            when {
                moviesViewState.progressTypes == ProgressTypes.MAIN_PROGRESS -> MoviesDefaultProgress()

                moviesViewState.moviesErrorModel != null -> ErrorMessage(
                    failure = moviesViewState.moviesErrorModel,
                    onRetry = onClickRetry
                )

                else -> {
                    MoviesList(
                        progressTypes = moviesViewState.progressTypes,
                        moviesList = moviesViewState.moviesList,
                        onBottomReached = onBottomReached,
                        onClickMovie = onMovieClick,
                    )
                    if (moviesViewState.progressTypes == ProgressTypes.FULL_PROGRESS) {
                        FullScreenLoader()
                    }
                }
            }
        }
    }
}


@Composable
private fun HandleAction(
    action: MoviesOneTimeAction,
    onMovieClick: (MovieListItem) -> Unit
) {
    LaunchedEffect(action) {
        when (action) {
            is MoviesOneTimeAction.NavigateToMovieDetails -> {
                onMovieClick(action.movieListItem)
            }
        }
    }
}

@Composable
fun FullScreenLoader() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        CircularProgressIndicator()
    }
}
