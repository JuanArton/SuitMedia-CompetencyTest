package com.juanarton.test.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juanarton.test.data.DataDummy
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.data.GuestDataClass
import com.juanarton.test.model.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository private  constructor(private val remoteDataSource: RemoteDataSource): RepositoryInterface {

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(): DataRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    instance = DataRepository(
                        RemoteDataSource.getInstance()
                    )
                }
                return instance as DataRepository
            }

        }
    }

    override fun getEventDummyData(): ArrayList<EventDataClass> {
        return DataDummy.getEventData()
    }

    override fun getGuestList(): LiveData<List<GuestDataClass>> {
        val listGuest = MutableLiveData<List<GuestDataClass>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getGuestList(object : RemoteDataSource.LoadGuestCallback{
                override fun onGuestReceived(guestResponse: ArrayList<GuestDataClass>) {
                    listGuest.postValue(guestResponse)
                }
            })
        }
        return listGuest
    }
}