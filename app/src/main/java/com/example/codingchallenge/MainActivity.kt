package com.example.codingchallenge

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.codingchallenge.Binding.BinderHandler
import com.example.codingchallenge.Model.Address
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.RecyclerviewBinding.adapter.ClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.LongClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder
import com.example.codingchallenge.Utils.SessionManager
import com.example.codingchallenge.databinding.RecyclerviewbindingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(),BinderHandler<Any> {

    lateinit var sessionManager: SessionManager
    lateinit var binding : RecyclerviewbindingBinding
    lateinit var userList : ObservableArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)

        if (!sessionManager.isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.view = this
        binding.list = userList


    }

    fun getUsers()
    {
                val retrofit = Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiInterface = retrofit.create(ApiInterface::class.java)
        val getUsers = apiInterface.getUsers()

        getUsers.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                println("message error = " + t.message)
            }

            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                val users: List<Users> = response.body()!!
                userList.clear()
                userList.addAll(users)

                val address: Address? = users[1].address
                println("users = $users")
                println("address = ${address!!.city}")


            }

        })
    }

    override fun clickHandler(): ClickHandler<Any>? {
        return null
    }

    override fun longClickHandler(): LongClickHandler<Any>? {
        return null
    }

    override fun itemViewBinder(): ItemBinder<Any>? {
        return null
    }



}
