package com.example.codingchallenge

import com.example.codingchallenge.Utils.ValidationUtils
import org.junit.Assert.*
import org.junit.Test

class ValidationUtilsTest {
    @Test
    fun testPasswordNotValid() {
        val listNotValidPassword = ArrayList<String>()
        listNotValidPassword.add("123456")
        listNotValidPassword.add("asdf1234")
        listNotValidPassword.add("asdf1234!")

        for (notValidPassword in listNotValidPassword) {
            println("list listNotValidPassword $notValidPassword")
            assertTrue(ValidationUtils.isPasswordValid(notValidPassword))
        }

    }

    @Test
    fun testPasswordValid() {
        val listValidPassword = ArrayList<String>()
        listValidPassword.add("P@ssw0rd")
        listValidPassword.add("Asdf1234@")
        listValidPassword.add("abcD1234!")

        for (validPassword in listValidPassword) {
            println("list validPassword $validPassword")
            assertTrue(ValidationUtils.isPasswordValid(validPassword))
        }

    }

    @Test
    fun isNull() {
        val string = ArrayList<String>()
        string.add("")
        string.add("null")
        string.add("  ")

        for (data in string) {
            assertTrue(ValidationUtils.isNull(data))
        }

    }
}