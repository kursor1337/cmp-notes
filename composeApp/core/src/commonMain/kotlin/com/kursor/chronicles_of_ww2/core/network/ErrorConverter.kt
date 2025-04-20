package com.kursor.chronicles_of_ww2.core.network

import com.kursor.chronicles_of_ww2.core.error_handling.ApplicationException
import com.kursor.chronicles_of_ww2.core.error_handling.DeserializationException
import com.kursor.chronicles_of_ww2.core.error_handling.NoInternetException
import com.kursor.chronicles_of_ww2.core.error_handling.ServerException
import com.kursor.chronicles_of_ww2.core.error_handling.ServerUnavailableException
import com.kursor.chronicles_of_ww2.core.error_handling.UnauthorizedException
import com.kursor.chronicles_of_ww2.core.error_handling.UnknownException
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.ContentConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.setupErrorConverter() {
    expectSuccess = true

    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, _ ->
            throw convertToApplicationException(cause)
        }
    }
}

private suspend fun convertToApplicationException(throwable: Throwable) = when (throwable) {
    is ApplicationException -> throwable
    is ClientRequestException -> when (throwable.response.status) {
        HttpStatusCode.GatewayTimeout, HttpStatusCode.ServiceUnavailable -> {
            ServerUnavailableException(throwable)
        }
        HttpStatusCode.Unauthorized -> UnauthorizedException(throwable)
        else -> mapBadRequestException(throwable)
    }
    is ContentConvertException -> DeserializationException(throwable)
    is SocketTimeoutException -> ServerUnavailableException(throwable)
    is IOException -> (throwable.cause as? ApplicationException) ?: NoInternetException(throwable)
    else -> UnknownException(throwable, throwable.message ?: "Unknown")
}

private suspend fun mapBadRequestException(exception: ClientRequestException): ApplicationException =
    try {
        val json = createDefaultJson()
        val errorBody = exception.response.bodyAsText()
        val errorMessage = null // TODO: parse errorBody

        ServerException(exception, errorMessage)
    } catch (e: Exception) {
        when (e) {
            is SerializationException, is IllegalArgumentException -> DeserializationException(e)
            else -> ServerException(e)
        }
    }