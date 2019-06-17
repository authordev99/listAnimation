package com.example.codingchallenge

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.codingchallenge.Model.Users
import com.example.codingchallenge.Utils.BindingPresenter
import com.example.codingchallenge.Utils.IntentUtils
import com.example.codingchallenge.databinding.ActivityDetailBinding
import com.google.gson.Gson


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
            BindingPresenter(this).openMap(users.address!!.geo!!.lat,users.address!!.geo!!.lng,users.address!!.street)
        }

        binding.phoneNumber.setOnClickListener {
            IntentUtils(this).openCall(users.phone)
        }

        binding.urlWebsite.setOnClickListener {
            IntentUtils(this).openURL(users.website)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
