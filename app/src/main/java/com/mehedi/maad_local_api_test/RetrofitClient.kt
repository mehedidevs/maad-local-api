package com.mehedi.maad_local_api_test

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private const val baseUrl = "http://172.31.7.7:8080/v1/api/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }


}