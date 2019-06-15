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
import com.example.codingchallenge.Utils.ValidationUtils
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

        binding.signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun login(view: View) {

        email = binding.email.text.toString()
        password = binding.password.text.toString()

        if (ValidationUtils.isNull(binding.spCountry.selectedItem.toString())) {
            Snackbar.make(view, "Select your country", Snackbar.LENGTH_LONG).show()
            return
        }

        val currentUser = sqliteHelper.authenticate(UserLogin(email, password))
        print("currentUser = $currentUser")
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            SessionManager(this).saveLoginSession(currentUser)
        } else {
            Snackbar.make(view, getString(R.string.app_failed_login), Snackbar.LENGTH_LONG).show()
        }

    }


}
