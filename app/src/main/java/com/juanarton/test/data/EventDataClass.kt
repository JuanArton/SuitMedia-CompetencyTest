package com.juanarton.test.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDataClass (
    val image: String,
    val nama: String,
    val tanggal: String
): Parcelable