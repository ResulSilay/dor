package org.dor.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class IPAddressDto(
    val query: String? = null,
)
