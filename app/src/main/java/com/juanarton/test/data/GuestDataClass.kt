package com.juanarton.test.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GuestDataClass (
    val image: String,
    val id: String,
    val nama: String,
    val birthdate: String
): Parcelable