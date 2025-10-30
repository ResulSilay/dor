package org.dor.domain.resource

import org.dor.data.remote.api.ApiResult

sealed class Resource<out T> {

    data class Loading(val isLoading: Boolean) : Resource<Nothing>()

    data class Success<T>(val data: T): Resource<T>()

    data class Error(val message: String, val code: Int? = null): Resource<Nothing>()
}

fun <T> ApiResult<T>.toResource(): Resource<T> = when (this) {
    is ApiResult.Loading -> Resource.Loading(isLoading)
    is ApiResult.Success -> Resource.Success(data)
    is ApiResult.Error -> Resource.Error(message)
}