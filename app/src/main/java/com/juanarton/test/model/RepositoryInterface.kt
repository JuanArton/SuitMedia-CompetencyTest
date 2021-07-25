package com.juanarton.test.model

import androidx.lifecycle.LiveData
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.data.GuestDataClass

interface RepositoryInterface {
    fun getEventDummyData(): ArrayList<EventDataClass>
    fun getGuestList(): LiveData<List<GuestDataClass>>?
}