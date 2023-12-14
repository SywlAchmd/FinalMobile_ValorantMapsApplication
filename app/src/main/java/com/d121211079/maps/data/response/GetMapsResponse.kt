package com.d121211079.maps.data.response

import com.d121211079.maps.data.models.Maps
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMapsResponse(
    @SerialName("data")
    val data: List<Maps>,
    @SerialName("status")
    val status: Int?
)