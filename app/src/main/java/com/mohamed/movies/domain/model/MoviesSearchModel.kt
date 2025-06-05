package com.mohamed.movies.domain.model

import com.mohamed.movies.utils.Constants

class MoviesSearchModel(
    val language: String = Constants.Defaults.DEFAULT_LANGUAGE,
    val page: Int,
    val query: String? = null
)