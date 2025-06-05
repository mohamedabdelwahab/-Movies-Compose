package com.mohamed.movies.ui.movieDetails.viewmodel

import com.mohamed.movies.domain.model.Failure
import com.mohamed.movies.domain.model.movieDetails.MovieDetailsReposeModel
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.mvi_base.Event
import com.mohamed.movies.ui.mvi_base.OneTimeAction
import com.mohamed.movies.ui.mvi_base.UIState

data class MovieDetailsViewState(
    val movieDetailsReposeModel: MovieDetailsReposeModel? = null,
    val progressTypes: ProgressTypes = ProgressTypes.NONE,
    val errorModel: Failure? = null,
) : UIState

sealed class MovieDetailsEvents : Event {
    data object LoadMovieDetails : MovieDetailsEvents()
    data object OnClickRetry : MovieDetailsEvents()
}

sealed class MovieDetailsOneTimeAction : OneTimeAction {
}

