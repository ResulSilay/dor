package org.dor.data.remote.mapper

import org.dor.data.remote.model.response.IPAddressDto
import org.dor.data.remote.model.response.IPInfoDto
import org.dor.domain.model.IPAddress
import org.dor.domain.model.IPInfo

class IPMapper {

    fun mapAddress(dto: IPAddressDto): IPAddress = IPAddress(
        ipAddress = dto.query.orEmpty()
    )

    fun mapInfo(dto: IPInfoDto): IPInfo = IPInfo(
        status = dto.status.orEmpty(),
        country = dto.country.orEmpty(),
        countryCode = dto.countryCode.orEmpty(),
        region = dto.region.orEmpty(),
        regionName = dto.regionName.orEmpty(),
        city = dto.city.orEmpty(),
        zip = dto.zip.orEmpty(),
        lat = dto.lat ?: 0.0,
        lon = dto.lon ?: 0.0,
        timezone = dto.timezone.orEmpty(),
        isp = dto.isp.orEmpty(),
        org = dto.org.orEmpty(),
        asInfo = dto.asInfo.orEmpty(),
        query = dto.query.orEmpty()
    )
}
