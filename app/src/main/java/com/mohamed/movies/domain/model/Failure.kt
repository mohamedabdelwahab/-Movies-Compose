package com.mohamed.movies.domain.model

sealed class Failure {
    data object NetworkConnection : Failure()
    data object  UnAuthorize : Failure()
    data class ServerError(val message:String?) : Failure()
}