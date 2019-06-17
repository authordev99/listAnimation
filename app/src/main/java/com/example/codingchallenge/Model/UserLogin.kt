package com.example.codingchallenge.Model

class UserLogin {

    constructor(username: String, password: String, email: String = "") {
        this.username = username
        this.password = password
        this.email = email
    }

    var username: String = ""
    var password: String = ""
    var email: String = ""

}