package com.juanarton.test.ui.guest

import androidx.lifecycle.ViewModel
import com.juanarton.test.model.DataRepository

class ListGuestViewModel (private val dataRepository: DataRepository): ViewModel() {
    val listGuest = dataRepository.getGuestList()
}