package com.example.codingchallenge.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Users : Serializable{

    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("username")
    var username: String = ""
    @SerializedName("email")
    var email: String = ""
    @SerializedName("address")
    var address : Address? = null
    @SerializedName("phone")
    var phone: String = ""
    @SerializedName("website")
    var website: String = ""
    @SerializedName("company")
    var company : Company? = null
}