package org.dor.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IPInfoDto(
    val status: String? = null,
    val country: String? = null,
    val countryCode: String? = null,
    val region: String? = null,
    val regionName: String? = null,
    val city: String? = null,
    val zip: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val timezone: String? = null,
    val isp: String? = null,
    val org: String? = null,
    @SerialName("as")
    val asInfo: String? = null,
    val query: String? = null
)