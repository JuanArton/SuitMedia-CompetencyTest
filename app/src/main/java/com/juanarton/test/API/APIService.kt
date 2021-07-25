package com.juanarton.test.API

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/v2/596dec7f0f000023032b8017")
    fun getPopular(
    ): Call<List<Response>>
}