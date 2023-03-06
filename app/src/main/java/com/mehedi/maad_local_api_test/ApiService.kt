package com.mehedi.maad_local_api_test

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {


    @GET("user")
    fun getAllUser(): Call<List<ResponseUser>>

    @POST("user")
    fun createUser(@Body user: User): Call<ResponseUser>

}