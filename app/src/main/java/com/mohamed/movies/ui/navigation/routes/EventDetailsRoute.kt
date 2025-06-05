package com.mohamed.movies.ui.navigation.routes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mohamed.movies.ui.movieDetails.MovieDetailsScreen
import com.mohamed.movies.ui.movieDetails.viewmodel.MovieDetailsViewModel
import com.mohamed.movies.ui.navigation.Screen
import com.mohamed.movies.ui.navigation.openComposable
import com.mohamed.movies.utils.Constants.NavigationArguments.MOVIE_DETAILS_ARGUMENT

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.movieDetailsRoute(navHostController: NavHostController) {

    openComposable(
        "${Screen.MovieDetails.route}/{$MOVIE_DETAILS_ARGUMENT}",
        arguments = listOf(navArgument(MOVIE_DETAILS_ARGUMENT) {
            type = NavType.IntType
        })
    ) {
        val viewModel: MovieDetailsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        MovieDetailsScreen(
            viewState = state,
            onEvent = { viewModel.sendEvent(it) }
        )
    }
}