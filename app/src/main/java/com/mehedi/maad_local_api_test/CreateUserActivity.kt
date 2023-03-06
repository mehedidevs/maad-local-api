package com.mehedi.maad_local_api_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mehedi.maad_local_api_test.databinding.ActivityCreateUserBinding
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class CreateUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.submitBtn.setOnClickListener {
            val user = User(binding.userEmail.text.toString(), binding.userName.text.toString())
            val apiService: ApiService = RetrofitClient.getRetrofitInstance()
                .create(ApiService::class.java)

            apiService.createUser(user).enqueue(object : retrofit2.Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {

                    Log.i("TAG", "onResponse: $response ")

                    if (response.isSuccessful) {

                        Toast.makeText(
                            this@CreateUserActivity,
                            "User Created Successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.i("TAG", "onResponse ${response.body()}")


                    }

                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {


                    Log.i("TAG", "onFailure: ${t.message}")

                }
            })


        }


    }
}