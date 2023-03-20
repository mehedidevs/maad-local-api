package com.mehedi.maad_local_api_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mehedi.maad_local_api_test.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), DeleteListener, UpdateListener {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserAdapter
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this, this)
        binding.userRCV.adapter = adapter


        apiService = RetrofitClient.getRetrofitInstance()
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

    override fun deleteUser(userId: Int) {

        val response = apiService.deleteUser(userId)


        response.enqueue(object : retrofit2.Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {

                if (response.isSuccessful) {
                    val userData = response.body()

                    Toast.makeText(
                        this@MainActivity,
                        "${userData?.name} deleted! ",
                        Toast.LENGTH_LONG
                    ).show()


                }


            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {

            }

        })


    }

    override fun updateUser(user: ResponseUser, userId: Int) {

        val intent = Intent(this, CreateUserActivity::class.java)
        intent.putExtra(CreateUserActivity.id_key, userId)
        intent.putExtra(CreateUserActivity.email_key, user.email)
        intent.putExtra(CreateUserActivity.name_key, user.name)

        startActivity(intent)


    }


}