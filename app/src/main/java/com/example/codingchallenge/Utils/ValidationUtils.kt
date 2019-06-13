package com.example.codingchallenge.Utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {

    fun isNull(text: String?): Boolean {

        return !(text != null && text != "" && text.trim { it <= ' ' }.isNotEmpty() && !text.matches("null".toRegex()))
    }


    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isPasswordValid(password: String): Boolean {

        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()\\[\\]*+,./:-;<=|>{?@^_`}~]).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)

        return matcher.matches()

    }
}