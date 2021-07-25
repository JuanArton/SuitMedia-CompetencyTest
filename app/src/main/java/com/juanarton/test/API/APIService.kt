package com.juanarton.test.API

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("/v2/596dec7f0f000023032b8017")
    fun getPopular(
    ): Call<List<Response>>
}