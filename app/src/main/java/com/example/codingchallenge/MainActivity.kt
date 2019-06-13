package com.example.codingchallenge

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.codingchallenge.Binding.BinderHandler
import com.example.codingchallenge.Model.Address
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.RecyclerviewBinding.adapter.ClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.LongClickHandler
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinderBase
import com.example.codingchallenge.Utils.SessionManager
import com.example.codingchallenge.databinding.RecyclerviewbindingBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), BinderHandler<Any> {

    lateinit var sessionManager: SessionManager
    lateinit var binding: RecyclerviewbindingBinding
    var userList = ObservableArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)

        supportActionBar!!.title = "Home"

        if (!sessionManager.isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = DataBindingUtil.setContentView(this, R.layout.recyclerviewbinding)
        binding.view = this
        binding.list = userList
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {
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
            }

        })
    }

    override fun clickHandler(): ClickHandler<Any>? {
        return ClickHandler { users ->
            startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra(PARAM_DETAIL_USERS, Gson().toJson(users))
            })
        }
    }

    override fun longClickHandler(): LongClickHandler<Any>? {
        return null
    }

    override fun itemViewBinder(): ItemBinder<Any>? {
        return ItemBinderBase<Any>(BR.users, R.layout.list_item_users)
    }

    companion object{
        const val PARAM_DETAIL_USERS = "detail_users"
    }


}
