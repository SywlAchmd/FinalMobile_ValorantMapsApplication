package com.d121211079.maps

import android.app.Application
import com.d121211079.maps.data.AppContainer
import com.d121211079.maps.data.DefaultAppContainer

class MyApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}