package com.d121211079.maps.data.repository

import com.d121211079.maps.data.response.GetMapsResponse
import com.d121211079.maps.data.source.remote.ApiService

class MapsRepository(private val apiService: ApiService) {

    suspend fun getMaps(): GetMapsResponse {
        return apiService.getMaps()
    }
}