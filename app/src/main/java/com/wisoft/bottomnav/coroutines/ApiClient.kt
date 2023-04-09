package com.wisoft.bottomnav.coroutines

import com.wisoft.bottomnav.DataClass.basicInfo
import com.wisoft.bottomnav.DataClass.listData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import retrofit2.http.*

interface ApiClient {
    @Headers("Content-Type: application/json")
    @GET("getbyID/")
    suspend fun getID(@QueryMap item:Map<String, String>): Response<basicInfo>

//    @Headers("Content-Type: application/json")
    @GET("getlist/")
    suspend fun getList(): Response<listData>
}