package com.juanarton.test.API

import com.google.gson.annotations.SerializedName

data class Response (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("birthdate")
    val birthdate: String
)