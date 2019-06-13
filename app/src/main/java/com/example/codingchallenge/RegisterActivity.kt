package com.example.codingchallenge

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.example.codingchallenge.Model.UserLogin
import com.example.codingchallenge.Utils.SqliteHelper
import com.example.codingchallenge.Utils.ValidationUtils
import com.example.codingchallenge.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var email: String
    lateinit var password: String
    lateinit var confirmPassword: String
    lateinit var sqliteHelper: SqliteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SqliteHelper(this)
        supportActionBar!!.title = "Sign Up"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    fun signUp(view: View) {

        email = binding.email.text.toString()
        password = binding.password.text.toString()
        confirmPassword = binding.confirmPassword.text.toString()

        if (!ValidationUtils.isEmailValid(email)) {
            binding.emailSignUp.error = "Please enter valid email"
            return
        } else {
            binding.emailSignUp.error = null
        }

        if (!ValidationUtils.isPasswordValid(password)) {
            binding.passwordSignUp.error = "Password shall be 8 characters and above in length, contain mixed case, alphanumeric and special characters."
            return
        } else {
            binding.passwordSignUp.error = null
        }

        if (!password.equals(confirmPassword, ignoreCase = true)) {
            binding.confirmPasswordSignUp.error = "Password should be same"
            return
        } else {
            binding.confirmPasswordSignUp.error = null
        }

        if (!sqliteHelper.isEmailExists(email)) {
            sqliteHelper.addUser(UserLogin(email, password))
            Snackbar.make(view, "User created successfully! Please Login ", Snackbar.LENGTH_SHORT).show()
            finish()
        } else {
            Snackbar.make(view, "User already exists with same email", Snackbar.LENGTH_SHORT).show()
        }
    }
}
