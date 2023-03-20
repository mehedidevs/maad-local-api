package com.mehedi.maad_local_api_test

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {


    @GET("user")
    fun getAllUser(): Call<List<ResponseUser>>

    @POST("user")
    fun createUser(@Body user: RequestUser): Call<ResponseUser>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id: Int): Call<ResponseUser>


    @PUT("user/{id}")
    fun updateUser(
        @Body user: ResponseUser,
        @Path("id") id: Int
    ): Call<ResponseUser>


}