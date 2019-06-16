package com.example.codingchallenge.Utils

import android.content.Context
import android.content.Intent
import android.net.Uri

open class BindingPresenter(val context: Context) {

    fun openMap(lat: String, long: String, title: String) {
        val mapUri = "http://maps.google.com/maps?q=loc:$lat,$long ($title)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUri))
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
        context.startActivity(intent)
    }
}