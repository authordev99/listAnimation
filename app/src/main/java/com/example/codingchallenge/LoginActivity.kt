package com.example.codingchallenge

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.View
import com.example.codingchallenge.Model.UserLogin
import com.example.codingchallenge.Utils.SessionManager
import com.example.codingchallenge.Utils.SqliteHelper
import com.example.codingchallenge.databinding.ActivityLoginBinding
import android.widget.ArrayAdapter
import java.util.*
import kotlin.collections.ArrayList


class LoginActivity : Activity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var username: String
    lateinit var password: String
    lateinit var sqliteHelper: SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SqliteHelper(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        setSpinnerCountry()
    }

    private fun setSpinnerCountry()
    {
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }

        countries.sort()
        for (country in countries) {
            println(country)
        }

        val countryAdapter = ArrayAdapter(this,
                R.layout.spinner_textview, countries)

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the your spinner
        binding.spCountry.adapter = countryAdapter
    }

    fun login(view: View) {

        binding.loading.visibility = View.VISIBLE
        username = binding.username.text.toString()
        password = binding.password.text.toString()


        val currentUser = sqliteHelper.authenticate(UserLogin(username, password,""))
        print("currentUser = $currentUser")
        if (currentUser != null) {
            binding.loading.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(p0: Animator?) {
                }

                override fun onAnimationCancel(p0: Animator?) {
                }

                override fun onAnimationStart(p0: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    binding.loading.setAnimation("done.json")
                    binding.loading.loop(false)
                    binding.loading.playAnimation()
                    Handler().postDelayed({

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        SessionManager(this@LoginActivity).saveLoginSession(currentUser)

                    }, 3000)

                    binding.loading.removeAnimatorListener(this)
                }
            })

            binding.loading.loop(false)

        } else {
            Snackbar.make(view, getString(R.string.app_failed_login), Snackbar.LENGTH_LONG).show()
            binding.loading.visibility = View.GONE
        }

    }


}
