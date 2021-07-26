package com.juanarton.test.ui.maps

import androidx.lifecycle.ViewModel
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.model.DataRepository

class MapsViewModel (private val dataRepository: DataRepository): ViewModel() {
    fun getEventDummyData(): ArrayList<EventDataClass>{
        return dataRepository.getEventDummyData()
    }
}