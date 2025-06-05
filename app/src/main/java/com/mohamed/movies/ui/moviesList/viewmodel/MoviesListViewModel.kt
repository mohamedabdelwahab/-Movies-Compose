package com.mohamed.movies.ui.moviesList.viewmodel

import androidx.lifecycle.viewModelScope
import com.mohamed.movies.domain.domain.getMovies.GetLatestMoviesUseCase
import com.mohamed.movies.domain.domain.getMovies.searchSuggestion.GetSearchSuggestionMoviesUseCase
import com.mohamed.movies.domain.model.Failure
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.model.moviesResponse.MovieListItem
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.mvi_base.MVIBaseViewModel
import com.mohamed.movies.utils.alternate
import com.mohamed.movies.utils.removeLeadingAndExtraSpaces
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetLatestMoviesUseCase,
    private val getSearchSuggestionMoviesUseCase: GetSearchSuggestionMoviesUseCase,
) : MVIBaseViewModel<MoviesViewState, MoviesEvents, MoviesOneTimeAction>() {

    private var isLoading = false
    private var searchJob: Job? = null

    override fun createInitialState(): MoviesViewState = MoviesViewState()

    override fun onEvent(event: MoviesEvents) {
        when (event) {
            MoviesEvents.LoadMovies -> fetchMovies(LoadType.Initial)
            MoviesEvents.OnPullToRefresh -> fetchMovies(LoadType.PullToRefresh)
            MoviesEvents.OnLoadMoreMovies -> fetchMovies(LoadType.Pagination)
            is MoviesEvents.OnClickMovies -> onMovieClicked(event.movie)
            is MoviesEvents.OnSearchQueryChanged -> onSearchQueryChanged(event.newText)
            MoviesEvents.OnClickRetry -> fetchMovies(LoadType.Initial)
            is MoviesEvents.OnSuggestionSelected -> onSuggestionSelected(event.movie)
            MoviesEvents.OnDismissSearch -> {
                setState { copy(showSuggestions = false) }
            }

            is MoviesEvents.OnSearchSubmit -> {
                setState { copy(showSuggestions = false) }
            }
        }
    }

    private fun onSuggestionSelected(movie: MovieListItem) {
        setState {
            copy(
                searchText = movie.title.alternate(),
                showSuggestions = false,
                searchSuggestions = arrayListOf()
            )
        }
    }

    private fun onSearchQueryChanged(query: String) {
        val trimmedQuery = query.removeLeadingAndExtraSpaces()

        if (query.isBlank()) {
            setState {
                copy(
                    searchSuggestions = arrayListOf(),
                    showSuggestions = false,
                    searchText = "",
                )
            }
            return
        } else
            setState {
                copy(
                    showSuggestions = true,
                    searchText = trimmedQuery,
                    searchSuggestions = searchSuggestions
                        ?.filter { it.title?.contains(query, ignoreCase = true) == true }
                        ?.let(::ArrayList) ?: arrayListOf()
                )
            }


        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400L) // Debounce input
            fetchSuggestion()
        }
    }

    private fun onMovieClicked(movie: MovieListItem) {
        // Extend for navigation
        // sendOneTimeAction(MoviesOneTimeAction.NavigateToDetails(movie.id))
    }

    private fun fetchSuggestion() {
        viewModelScope.launch {
            getSearchSuggestionMoviesUseCase(currentState.searchText).collect { result ->
                when (result) {
                    is Resource.Success -> setState {
                        copy(
                            searchSuggestions = result.data,
                        )
                    }

                    is Resource.Error -> {}
                }
            }
        }
    }


    private fun fetchMovies(loadType: LoadType) {
        val state = currentState
        if (isLoading || (loadType == LoadType.Pagination && !state.canPaginate)) return

        val targetPage = when (loadType) {
            LoadType.Initial, LoadType.PullToRefresh -> 1
            LoadType.Pagination -> state.page + 1
        }

        setState {
            copy(
                progressTypes = loadType.toProgressType(),
                moviesErrorModel = null
            )
        }

        isLoading = true

        viewModelScope.launch {
            getMoviesListUseCase(page = targetPage).collect { result ->
                when (result) {
                    is Resource.Success -> onMoviesLoaded(
                        movies = result.data.movieListItems.orEmpty(),
                        page = targetPage,
                        loadType = loadType
                    )

                    is Resource.Error -> onMoviesFailed(result.error)
                    else -> Unit
                }
                isLoading = false
            }
        }
    }

    private fun onMoviesLoaded(movies: List<MovieListItem>, page: Int, loadType: LoadType) {
        val newList = when (loadType) {
            LoadType.Initial, LoadType.PullToRefresh -> ArrayList(movies)
            LoadType.Pagination -> ArrayList(currentState.moviesList.orEmpty() + movies)
        }

        setState {
            copy(
                moviesList = newList,
                page = page,
                canPaginate = movies.isNotEmpty(),
                progressTypes = ProgressTypes.NONE,
                moviesErrorModel = null
            )
        }
    }

    private fun onMoviesFailed(error: Failure) {
        setState {
            copy(
                progressTypes = ProgressTypes.NONE,
                moviesErrorModel = error
            )
        }
    }

    private enum class LoadType {
        Initial, PullToRefresh, Pagination
    }

    private fun LoadType.toProgressType(): ProgressTypes = when (this) {
        LoadType.Initial -> ProgressTypes.MAIN_PROGRESS
        LoadType.PullToRefresh -> ProgressTypes.PULL_TO_REFRESH_PROGRESS
        LoadType.Pagination -> ProgressTypes.PAGING_PROGRESS
    }
}
