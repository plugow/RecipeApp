package com.plugow.recipeapp.util.errorhandling

import com.plugow.recipeapp.data.Either
import com.plugow.recipeapp.data.left
import com.plugow.recipeapp.data.right
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

suspend fun <T> handleRequest(requestFunc: suspend () -> T): Either<Failure, T> {
    return try {
        val response = requestFunc.invoke()
        return response.right()
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> Failure.NetworkConnection.left()
            is TimeoutException, is SocketTimeoutException -> Failure.TimeOut.left()
            is HttpException -> {
                when (exception.code()) {
                    500 -> Failure.ServerError.left()
                    503 -> Failure.ServiceUnavailable.left()
                    401 -> Failure.Unauthorized.left()
                    else -> Failure.Unknown.left()
                }
            }
            else -> {
                Failure.ServerError.left()
            }
        }
    }
}