package org.dor.data.remote.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import org.dor.data.remote.api.ApiResult
import org.dor.data.remote.api.apiCall
import org.dor.data.remote.model.response.IPAddressDto
import org.dor.data.remote.model.response.IPInfoDto

class IPService(
    private val client: HttpClient,
) {

    suspend fun getAddress(): Flow<ApiResult<IPAddressDto>> = apiCall {
        client.get(urlString = "json?fields")
    }

    suspend fun getInfo(ipAddress: String): Flow<ApiResult<IPInfoDto>> = apiCall {
        client.get(urlString = "json/$ipAddress")
    }
}
