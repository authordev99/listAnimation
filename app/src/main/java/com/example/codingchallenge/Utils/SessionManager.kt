package com.example.codingchallenge.Utils

import android.content.Context
import android.content.SharedPreferences
import com.example.codingchallenge.Model.UserLogin
import com.google.gson.Gson

class SessionManager(var context: Context) {

    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor
    internal var PRIVATE_MODE = 0

    val isLoggedIn: Boolean
        get() {
            print("isLoggedIn() is " + pref.getBoolean(IS_LOGIN, false))
            return pref.getBoolean(IS_LOGIN, false)
        }

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    fun saveLoginSession(userLogin: UserLogin) {
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()
    }

    fun saveObject(KEY: String, value: Any) {
        editor.putString(KEY, Gson().toJson(value))
        editor.commit()
    }


    fun clearSession() {
        editor.clear()
        editor.commit()
    }

    companion object {

        val IS_LOGIN = "logged_in"
        val PREF_NAME = "session"
    }
}