package com.juanarton.test.ui.event

import androidx.lifecycle.ViewModel
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.model.DataRepository

class ListEventViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun getEventDummyData(): ArrayList<EventDataClass>{
        return dataRepository.getEventDummyData()
    }
}