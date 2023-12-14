package com.d121211079.maps.data.source.remote

import com.d121211079.maps.data.response.GetMapsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/maps")
    suspend fun getMaps(): GetMapsResponse
}