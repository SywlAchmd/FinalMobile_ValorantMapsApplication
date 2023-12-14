package com.d121211079.maps.ui.activities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211079.maps.MyApplication
import com.d121211079.maps.data.models.Maps
import com.d121211079.maps.data.repository.MapsRepository
import java.io.IOException
import kotlinx.coroutines.launch

sealed interface MainUiState {
    data class Success(val maps: List<Maps>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val mapsRepository: MapsRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getMaps() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = mapsRepository.getMaps()
            Log.d("MainViewModel", "getMaps: ${result.data?.size}")
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            Log.d("MainViewMode", "getMaps error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getMaps()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val mapsRepository = application.container.mapsRepository
                MainViewModel(mapsRepository)
            }
        }
    }
}