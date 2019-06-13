package com.example.codingchallenge

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.codingchallenge.Model.UserLogin
import com.example.codingchallenge.Utils.SessionManager
import com.example.codingchallenge.Utils.SqliteHelper
import com.example.codingchallenge.databinding.ActivityLoginBinding

class LoginActivity : Activity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var email: String
    lateinit var password: String
    lateinit var sqliteHelper: SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SqliteHelper(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.signUp.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    fun login(view: View) {

        email = binding.email.text.toString()
        password = binding.password.text.toString()

        val currentUser = sqliteHelper.authenticate(UserLogin(email, password))
        print("currentUser = $currentUser")
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            SessionManager(this).saveLoginSession(currentUser)
        } else {
            Snackbar.make(view, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show()
        }

    }


}
