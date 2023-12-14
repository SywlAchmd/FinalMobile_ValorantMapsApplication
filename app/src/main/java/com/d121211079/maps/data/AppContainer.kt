package com.d121211079.maps.data

import com.d121211079.maps.data.repository.MapsRepository
import com.d121211079.maps.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val mapsRepository: MapsRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://valorant-api.com"

    val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val mapsRepository: MapsRepository
        get() = MapsRepository(retrofitService)

}