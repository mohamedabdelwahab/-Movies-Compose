package com.mohamed.movies.ui.navigation.routes

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mohamed.movies.ui.moviesList.MoviesScreen
import com.mohamed.movies.ui.moviesList.viewmodel.MoviesViewModel
import com.mohamed.movies.ui.navigation.Screen
import com.mohamed.movies.ui.navigation.openComposable


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.moviesListRoute(navController: NavHostController) {
    openComposable(Screen.MovieList.route) {
        val moviesListViewModel: MoviesViewModel = hiltViewModel()
        val state by moviesListViewModel.state.collectAsStateWithLifecycle()
        val action by moviesListViewModel.action.collectAsStateWithLifecycle(initialValue = null)
        MoviesScreen(
            moviesViewState = state,
            onEvent = moviesListViewModel::sendEvent,
            moviesOneTimeAction = action,
            onMovieClick = { movie ->
                navController.navigate(
                    Screen.MovieDetails.route + "/" + movie.id
                )
            }
        )
    }

}