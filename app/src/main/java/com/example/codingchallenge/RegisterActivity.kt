package com.example.codingchallenge

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.codingchallenge.Model.UserLogin
import com.example.codingchallenge.Utils.SqliteHelper
import com.example.codingchallenge.Utils.ValidationUtils
import com.example.codingchallenge.databinding.ActivityRegisterBinding

class RegisterActivity : Activity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var email: String
    lateinit var password: String
    lateinit var confirmPassword: String
    lateinit var sqliteHelper: SqliteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SqliteHelper(this)
       // supportActionBar!!.title = getString(R.string.app_sign_up)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    fun signUp(view: View) {

        email = binding.email.text.toString()
        password = binding.password.text.toString()
        confirmPassword = binding.confirmPassword.text.toString()

        if (!ValidationUtils.isEmailValid(email)) {
            binding.emailSignUp.error = getString(R.string.app_valid_email)
            return
        } else {
            binding.emailSignUp.error = null
        }

        if (!ValidationUtils.isPasswordValid(password)) {
            binding.passwordSignUp.error = getString(R.string.app_valid_password)
            return
        } else {
            binding.passwordSignUp.error = null
        }

        if (!password.equals(confirmPassword, ignoreCase = true)) {
            binding.confirmPasswordSignUp.error = getString(R.string.app_confirm_password)
            return
        } else {
            binding.confirmPasswordSignUp.error = null
        }

        if (!sqliteHelper.isEmailExists(email)) {
            sqliteHelper.addUser(UserLogin(email, password))
            Snackbar.make(view, getString(R.string.app_success_register), Snackbar.LENGTH_SHORT).show()
            finish()
        } else {
            Snackbar.make(view, getString(R.string.app_confirm_email), Snackbar.LENGTH_SHORT).show()
        }
    }
}
