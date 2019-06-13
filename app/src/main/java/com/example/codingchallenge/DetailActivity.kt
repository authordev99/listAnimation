package com.example.codingchallenge

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.databinding.ActivityDetailBinding
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    var users = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        users = Gson().fromJson(intent.extras!!.getString(MainActivity.PARAM_DETAIL_USERS), Users::class.java)
        binding.users = users
    }
}
