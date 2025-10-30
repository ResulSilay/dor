package org.dor.domain.repository

import kotlinx.coroutines.flow.Flow
import org.dor.data.remote.api.ApiResult
import org.dor.domain.model.IPAddress
import org.dor.domain.model.IPInfo

interface IPRepository {

    suspend fun getAddress(): Flow<ApiResult<IPAddress>>

    suspend fun getInfo(ipAddress: String): Flow<ApiResult<IPInfo>>
}