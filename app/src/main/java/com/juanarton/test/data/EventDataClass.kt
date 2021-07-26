package com.juanarton.test.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDataClass (
    val image: String,
    val nama: String,
    val tanggal: String,
    val loc: LatLng
): Parcelable