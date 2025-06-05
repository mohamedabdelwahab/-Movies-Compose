package com.mohamed.movies.ui.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mohamed.movies.ui.navigation.Screen
import com.mohamed.movies.ui.navigation.openComposable
import com.mohamed.movies.utils.Constants.NavigationArguments.MOVIE_DETAILS_ARGUMENT

fun NavGraphBuilder.movieDetailsRoute(navHostController: NavHostController) {

    openComposable(
        Screen.EventDetails.route,
        arguments = listOf(navArgument(MOVIE_DETAILS_ARGUMENT) {
            type = NavType.StringType
        })
    ) {
//        val viewModel: EventDetailsViewModel = hiltViewModel()
//        val state by viewModel.state.collectAsStateWithLifecycle()
//        val context = LocalContext.current
//        val sharedEventsViewModel =
//            viewModel<SharedEventsViewModel>(LocalContext.current as ComponentActivity)
//
//        val requestDetailsHistoryModel: RequestDetailsHistoryViewModel = hiltViewModel()
//        val requestDetailsHistoryViewState =
//            requestDetailsHistoryModel.state.collectAsStateWithLifecycle().value
//
//        LaunchedEffect(Unit) {
//            viewModel.action.collect { action ->
//                handleEventDetailsAction(
//                    context = context,
//                    action = action,
//                    navigateToEventForm = { event ->
//                        navigateToServiceForm(
//                            navController = navHostController,
//                            serviceId = event.serviceId.toString(),
//                            eventId = event.id,
//                            autoSubmitApplicants = event.autoSubmitApplicants,
//                            serviceName = event.title.toString()
//                        )
//
//                    },
//                    navigateBack = {
//                        navHostController.navigateUp()
//                    },
//                    navigateToLogin = {
//                        navigateToLogin(LoginRedirection.EVENTS_DETAILS, false, navHostController)
//                    },
//                    navigateToProfile = {
//                        navigateTo(
//                            Screen.Home.route.createRouteWithArguments(
//                                mapOf(TAB_INDEX to TAB_PROFILE)
//                            ),
//                            navHostController,
//                            true
//                        )
//                    },
//                    navigateToRequestForm = { formDataResponseItem, eventDetails, requestId ->
//                        sharedEventsViewModel.setFormData(formDataResponseItem)
//                        navigateToServiceForm(
//                            navController = navHostController,
//                            serviceId = eventDetails.serviceId.toString(),
//                            eventId = eventDetails.id,
//                            autoSubmitApplicants = eventDetails.autoSubmitApplicants,
//                            serviceName = eventDetails.title.toString(),
//                            requestId = requestId
//                        )
//                    }
//                )
//            }
//        }
//
//        val updatedLoginStatus =
//            navHostController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(
//                AFTER_NEED_LOGIN_REDIRECTION
//            )
//
//        LaunchedEffect(updatedLoginStatus) {
//            if (updatedLoginStatus == true) {
//                viewModel.sendEvent(EventDetailsEvent.LoadEventDetails)
//
//                navHostController.currentBackStackEntry
//                    ?.savedStateHandle
//                    ?.set(AFTER_NEED_LOGIN_REDIRECTION, false)
//            }
//        }
//
//        EventDetailsContent(
//            requestDetailsHistoryViewState = requestDetailsHistoryViewState,
//            state = state,
//            onEvent = { event -> viewModel.sendEvent(event) },
//            onGetRequestDetailsHistoryEvent = { requestDetailsHistoryModel.sendEvent(it) },
//        )
    }
}