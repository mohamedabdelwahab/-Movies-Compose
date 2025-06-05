package com.mohamed.movies.domain.model

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: Failure) : Resource<Nothing>()
}