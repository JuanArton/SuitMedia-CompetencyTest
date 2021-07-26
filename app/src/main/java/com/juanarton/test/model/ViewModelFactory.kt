package com.juanarton.test.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanarton.test.ui.event.ListEventViewModel
import com.juanarton.test.ui.guest.ListGuestViewModel
import com.juanarton.test.ui.maps.MapsViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    DataRepository.getInstance()
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ListEventViewModel::class.java) -> {
                ListEventViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ListGuestViewModel::class.java) -> {
                ListGuestViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}