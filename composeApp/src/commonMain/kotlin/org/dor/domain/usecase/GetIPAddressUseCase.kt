package org.dor.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.dor.domain.model.IPAddress
import org.dor.domain.repository.IPRepository
import org.dor.domain.resource.Resource
import org.dor.domain.resource.toResource

class GetIPAddressUseCase(
    private val repository: IPRepository,
) {

    suspend operator fun invoke(): Flow<Resource<IPAddress>> =
        repository.getAddress().map { result ->
            result.toResource()
        }
}

