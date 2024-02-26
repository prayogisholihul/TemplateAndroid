package com.zogik.core

/**
 * Created by : Prayogi Sholihul
 * Mailto : prayogisholihul@gmail.com
 * Created at : Monday 26/02/2024: 14:34
 **/

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseCall {
    suspend fun <ResultType> response(api: suspend () -> Response<ResultType>): Flow<Result<ResultType>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    emit(Result.Loading)
                    val response: Response<ResultType> = api()
                    emit(Result.Idle)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(Result.Success(data = it))
                        } ?: emit(Result.Error(message = "Unknown error occurred", code = 0))
                    } else {
                        emit(
                            Result.Error(
                                message = parserErrorBody(response.errorBody()),
                                code = response.code(),
                            ),
                        )
                    }
                } catch (e: Exception) {
                    emit(Result.Error(message = message(e), code = code(e)))
                }
            }
        }
    }

    private fun parserErrorBody(response: ResponseBody?): String {
        return response?.let {
            val json = JSONObject(it.string())
            val errorMessage = json.getString("message")
            errorMessage.ifEmpty { "Whoops! Something went wrong" }
            errorMessage
        } ?: "Unknown error occur, please try again"
    }

    private fun message(throwable: Throwable?): String {
        return when (throwable) {
            is SocketTimeoutException -> "Whoops! connection time out, try again!"
            is IOException -> "No internet connection, try again!"
            is HttpException -> try {
                val errorJsonString = throwable.response()?.errorBody()?.string().orEmpty()
                val json = JSONObject(errorJsonString)
                val errorMessage = json.getString("message")
                errorMessage.ifEmpty { "Whoops! Something went wrong" }
            } catch (e: Exception) {
                "Unknown error occur, please try again!"
            }

            else -> "Unknown error occur, please try again!"
        }
    }

    private fun code(throwable: Throwable?): Int {
        return when (throwable) {
            is HttpException -> (throwable).code()
            else -> 0
        }
    }
}