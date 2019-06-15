package com.example.codingchallenge.Utils

import android.support.v4.util.PatternsCompat
import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {

    fun isNull(text: String?): Boolean {

        return !(text != null && text != "" && text.trim { it <= ' ' }.isNotEmpty() && !text.matches("null".toRegex()))
    }


    fun isEmailValid(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isPasswordValid(password: String): Boolean {
        // 1 Capital Case, 1 Symbol, 8 character
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()\\[\\]*+,./:-;<=|>{?@^_`}~]).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)

        return matcher.matches()

    }
}