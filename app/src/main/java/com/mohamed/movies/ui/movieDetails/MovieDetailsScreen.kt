package com.mohamed.movies.ui.movieDetails

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohamed.movies.ui.model.ProgressTypes
import com.mohamed.movies.ui.movieDetails.viewmodel.MovieDetailsEvents
import com.mohamed.movies.ui.movieDetails.viewmodel.MovieDetailsViewState
import com.mohamed.movies.ui.moviesList.components.ErrorMessage
import com.mohamed.movies.ui.moviesList.components.MoviesDefaultProgress

@ExperimentalMaterial3Api
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    viewState: MovieDetailsViewState,
    onEvent: (MovieDetailsEvents) -> Unit = {},
) {

    when {
        viewState.progressTypes == ProgressTypes.MAIN_PROGRESS -> MoviesDefaultProgress()

        viewState.errorModel != null -> ErrorMessage(
            failure = viewState.errorModel,
            onRetry = { onEvent(MovieDetailsEvents.OnClickRetry) }
        )

        else -> {
            viewState.movieDetailsReposeModel?.let {
                MovieDetailContent(
                    modifier = modifier,
                    movie = it
                )
            }
        }
    }


}