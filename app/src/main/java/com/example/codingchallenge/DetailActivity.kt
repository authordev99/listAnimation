package com.example.codingchallenge

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.databinding.ActivityDetailBinding
import com.google.gson.Gson
import android.content.Intent
import android.net.Uri


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    var users = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.title = getString(R.string.app_detail_user)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        users = Gson().fromJson(intent.extras!!.getString(MainActivity.PARAM_DETAIL_USERS), Users::class.java)
        binding.users = users

        binding.location.setOnClickListener {
            val mapUri = "http://maps.google.com/maps?q=loc:${users.address!!.geo!!.lat},${users.address!!.geo!!.lng} ("+ users.address!!.street+")"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUri))
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
            startActivity(intent)
        }
    }
}
