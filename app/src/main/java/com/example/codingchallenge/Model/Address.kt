package com.example.codingchallenge.Model

import okhttp3.Address

class Address{

    constructor(street: String, suite: String, city: String, zipcode: String, geo: Geo?) {
        this.street = street
        this.suite = suite
        this.city = city
        this.zipcode = zipcode
        this.geo = geo
    }

    var street: String = ""
    var suite: String = ""
    var city: String = ""
    var zipcode : String = ""
    var geo : Geo? = null
}