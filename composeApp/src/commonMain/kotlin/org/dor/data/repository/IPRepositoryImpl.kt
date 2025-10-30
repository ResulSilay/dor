package org.dor.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dor.data.remote.api.ApiResult
import org.dor.data.remote.mapper.IPMapper
import org.dor.data.remote.service.IPService
import org.dor.domain.model.IPAddress
import org.dor.domain.model.IPInfo
import org.dor.domain.repository.IPRepository

class IPRepositoryImpl(
    private val api: IPService,
    private val mapper: IPMapper,
) : IPRepository {

    override suspend fun getAddress(): Flow<ApiResult<IPAddress>> =
        api.getAddress().map { result ->
            when (result) {
                is ApiResult.Success -> ApiResult.Success(data = mapper.mapAddress(dto = result.data))
                is ApiResult.Error -> result
                is ApiResult.Loading -> result
            }
        }

    override suspend fun getInfo(ipAddress: String): Flow<ApiResult<IPInfo>> =
        api.getInfo(ipAddress = ipAddress).map { result ->
            when (result) {
                is ApiResult.Success -> ApiResult.Success(data = mapper.mapInfo(dto = result.data))
                is ApiResult.Error -> result
                is ApiResult.Loading -> result
            }
        }

}
