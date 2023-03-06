package com.mehedi.maad_local_api_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.mehedi.maad_local_api_test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= UserAdapter()
        binding.userRCV.adapter= adapter


        val apiService: ApiService = RetrofitClient.getRetrofitInstance()
            .create(ApiService::class.java)


        apiService.getAllUser().enqueue(object : retrofit2.Callback<List<ResponseUser>> {
            override fun onResponse(
                call: Call<List<ResponseUser>>,
                response: Response<List<ResponseUser>>
            ) {

                if (response.isSuccessful) {

                    val responseData = response.body()

                  adapter.submitList(responseData)
                }


            }

            override fun onFailure(call: Call<List<ResponseUser>>, t: Throwable) {

            }
        })



        binding.createUserBtn.setOnClickListener {

            startActivity(Intent(this, CreateUserActivity::class.java))


        }


    }
}