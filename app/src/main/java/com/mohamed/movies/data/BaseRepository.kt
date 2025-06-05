package com.mohamed.movies.data

import com.mohamed.movies.utils.connection_utils.IConnectionUtils
import com.mohamed.movies.domain.model.Failure
import com.mohamed.movies.domain.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.HttpsURLConnection

abstract class BaseRepository(
    private val connectionUtils: IConnectionUtils
) {

    fun <T> safeApiCall(apiCall: suspend () -> T): Flow<Resource<T>> = flow {
        if (!connectionUtils.isConnected()) {
            emit(Resource.Error(Failure.NetworkConnection))
            return@flow
        }

        try {
            val result = apiCall()
            emit(Resource.Success(result))
        } catch (throwable: Throwable) {
            emit(Resource.Error(mapThrowableToFailure(throwable)))
        }
    }.flowOn(Dispatchers.IO)

    private fun mapThrowableToFailure(throwable: Throwable): Failure {
        return when (throwable) {
            is HttpException -> handleHttpException(throwable)
            is SocketTimeoutException -> Failure.NetworkConnection
            is IOException -> Failure.NetworkConnection
            else -> Failure.ServerError(throwable.localizedMessage)
        }
    }

    private fun handleHttpException(httpException: HttpException): Failure {
        return when (httpException.code()) {
            HttpsURLConnection.HTTP_UNAUTHORIZED -> Failure.UnAuthorize
            HttpsURLConnection.HTTP_GATEWAY_TIMEOUT,
            HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> Failure.NetworkConnection

            else -> {
                val message = extractErrorMessage(httpException)
                Failure.ServerError(message)
            }
        }
    }

    private fun extractErrorMessage(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
            errorBody ?: exception.message()
        } catch (e: Exception) {
            exception.message()
        }
    }
}
