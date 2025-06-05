@file:OptIn(ExperimentalMaterial3Api::class)

package com.mohamed.movies.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohamed.movies.ui.navigation.routes.movieDetailsRoute
import com.mohamed.movies.ui.navigation.routes.moviesListRoute
import com.mohamed.movies.utils.Constants

@Composable
fun AppNavigation(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = Screen.MovieList.route) {
        moviesListRoute(navController)
        movieDetailsRoute(navController)
    }
}


fun NavGraphBuilder.openComposable(
    screen: String,
    enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = slideInto(
        AnimatedContentTransitionScope.SlideDirection.Start
    ),
    exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = slideOut(
        AnimatedContentTransitionScope.SlideDirection.Start
    ),
    popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = slideInto(
        AnimatedContentTransitionScope.SlideDirection.End
    ),
    popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = slideOut(
        AnimatedContentTransitionScope.SlideDirection.End
    ),
    arguments: List<NamedNavArgument> = emptyList(),
    desiredScreenToBeOpened: @Composable ((NavBackStackEntry) -> Unit),
) {
    composable(
        screen,
        arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popExitTransition = popExitTransition,
        popEnterTransition = popEnterTransition
    ) {
        desiredScreenToBeOpened.invoke(it)
    }
}

private fun slideOut(slideDirection: AnimatedContentTransitionScope.SlideDirection): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutOfContainer(
            slideDirection, tween(Constants.TRANSITION_ANIMATION_DURATION)
        )
    }

private fun slideInto(slideDirection: AnimatedContentTransitionScope.SlideDirection): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideIntoContainer(
            slideDirection, tween(Constants.TRANSITION_ANIMATION_DURATION)
        )
    }



