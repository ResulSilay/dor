package org.dor.data.remote.api

sealed class ApiResult<out T> {

    data class Loading(val isLoading: Boolean) : ApiResult<Nothing>()

    data class Success<T>(val data: T): ApiResult<T>()

    data class Error(val message: String, val code: Int? = null): ApiResult<Nothing>()
}
