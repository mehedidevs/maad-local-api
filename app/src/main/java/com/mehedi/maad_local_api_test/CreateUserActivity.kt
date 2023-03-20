package com.mehedi.maad_local_api_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mehedi.maad_local_api_test.databinding.ActivityCreateUserBinding
import retrofit2.Call
import retrofit2.Response

class CreateUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateUserBinding
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(id_key)) {
            binding.submitBtn.text = update
            val name = intent.getStringExtra(name_key)
            val email = intent.getStringExtra(email_key)
            id = intent.getIntExtra(id_key, 0)
            binding.userName.setText(name)
            binding.userEmail.setText(email)

        }



        binding.submitBtn.setOnClickListener {

            val apiService: ApiService = RetrofitClient.getRetrofitInstance()
                .create(ApiService::class.java)
            if (binding.submitBtn.text == update) {


                val user = ResponseUser(
                    id,
                    binding.userEmail.text.toString(),
                    binding.userName.text.toString(),
                )
                val response = apiService.updateUser(user, id)

                response.enqueue(object : retrofit2.Callback<ResponseUser> {
                    override fun onResponse(
                        call: Call<ResponseUser>,
                        response: Response<ResponseUser>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@CreateUserActivity,
                                "${user.toString()} Updated ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {

                    }

                })


            } else {
                val user =
                    RequestUser(binding.userEmail.text.toString(), binding.userName.text.toString())

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

    companion object {
        const val id_key = "id"
        const val name_key = "name"
        const val email_key = "email"
        const val update = "Update"

    }


}