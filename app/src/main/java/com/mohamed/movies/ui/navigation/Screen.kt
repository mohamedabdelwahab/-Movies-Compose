package com.mohamed.movies.ui.navigation

import com.mohamed.movies.utils.Constants.NavigationArguments.MOVIE_DETAILS_ARGUMENT


sealed class Screen(val route: String) {
    data object MovieList : Screen("movieList}")
    data object EventDetails : Screen("movieDetails/{$MOVIE_DETAILS_ARGUMENT}")

}
