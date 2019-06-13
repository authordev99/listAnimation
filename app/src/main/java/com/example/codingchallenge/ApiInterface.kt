package com.example.codingchallenge

import com.example.codingchallenge.Model.Users
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

    companion object {
        var BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("users")
    fun getUsers(): Call<List<Users>>

}