package com.mohamed.movies.utils


object Constants {


    object Network {
        const val CONNECT_TIMEOUT: Long = 12
        const val READ_TIMEOUT: Long = 12
        const val WRITE_TIMEOUT: Long = 12
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
    }

    object NavigationArguments {
        const val MOVIE_DETAILS_ARGUMENT = "movie_details_navigation_argument"
    }
    const val EXTRA_SPACES_PATTERN = "\\s{2,}"

    const val TRANSITION_ANIMATION_DURATION = 900

    object Defaults {
        const val EMPTY_STRING = ""
        const val DEFAULT_LANGUAGE = "en"
        const val DEFAULT_PAGE_SIZE = 10
    }


    object URL {

        const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"
        const val BASE_NETWORK_URL = "https://api.themoviedb.org/"
    }

    object APIKeys {
        const val NEWS_API_KEY = "533af958594143758318137469b41ba9"
    }


}