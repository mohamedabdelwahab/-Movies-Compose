package com.mohamed.movies.ui.movieDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mohamed.movies.domain.model.Resource
import com.mohamed.movies.domain.useCase.movieDetails.GetMovieDetailsUseCase
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.mvi_base.MVIBaseViewModel
import com.mohamed.movies.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val mSavedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : MVIBaseViewModel<MovieDetailsViewState, MovieDetailsEvents, MovieDetailsOneTimeAction>() {
    val movieID = mSavedStateHandle.get<Int>(Constants.NavigationArguments.MOVIE_DETAILS_ARGUMENT)

    override fun createInitialState(): MovieDetailsViewState = MovieDetailsViewState()

    init {
        fetchMovieDetails()
    }

    override fun onEvent(event: MovieDetailsEvents) {
        when (event) {
            MovieDetailsEvents.LoadMovieDetails -> fetchMovieDetails()
            MovieDetailsEvents.OnClickRetry -> fetchMovieDetails()
        }
    }


    private fun fetchMovieDetails() {
        if (movieID == null)
            return

        setState {
            copy(
                progressTypes = ProgressTypes.MAIN_PROGRESS,
                errorModel = null
            )
        }

        viewModelScope.launch {
            getMovieDetailsUseCase(movieID).collect { result ->
                when (result) {
                    is Resource.Success -> setState {
                        copy(
                            movieDetailsReposeModel = result.data,
                            progressTypes = ProgressTypes.NONE
                        )
                    }

                    is Resource.Error -> {
                        setState {
                            copy(
                                progressTypes = ProgressTypes.NONE,
                                errorModel = result.error
                            )
                        }
                    }
                }
            }
        }
    }


}
