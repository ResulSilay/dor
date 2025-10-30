package org.dor.data.remote.api

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

inline fun <reified T> apiCall(
    crossinline block: suspend () -> HttpResponse,
): Flow<ApiResult<T>> = flow {
    block().run {
        if (status.isSuccess()) {
            emit(value = ApiResult.Success(data = body<T>()))
        } else {
            emit(
                value = ApiResult.Error(
                    message = bodyAsText(),
                    code = status.value
                )
            )
        }
    }
}.onStart {
    emit(value = ApiResult.Loading(isLoading = true))
}.catch { e ->
    emit(value = ApiResult.Error(message = e.message.orEmpty()))
}.onCompletion {
    emit(value = ApiResult.Loading(isLoading = false))
}