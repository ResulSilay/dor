package org.dor.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dor.domain.model.IPInfo
import org.dor.domain.repository.IPRepository
import org.dor.domain.resource.Resource
import org.dor.domain.resource.toResource

class GetIPInfoUseCase(
    private val repository: IPRepository,
) {

    suspend operator fun invoke(ipAddress: String): Flow<Resource<IPInfo>> =
        repository.getInfo(ipAddress = ipAddress).map { result ->
            result.toResource()
        }
}
