package com.example.codingchallenge.Utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.widget.Toast

class IntentUtils(private val context: Context) {

    fun openCall(phoneNumber: String) {
        if (!ValidationUtils.isNull(phoneNumber)) {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:" + Uri.encode(phoneNumber.trim { it <= ' ' }))
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(callIntent)
        } else {
            Toast.makeText(context, "Phone Number Unavalaible", Toast.LENGTH_SHORT).show()
        }
    }

    fun openURL(url: String) {

        var url = url
        if (!ValidationUtils.isNull(url)) {

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }

            val builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)

            val customTabsIntent = builder.build()
            try {
                customTabsIntent.launchUrl(context, Uri.parse(url))
            } catch (e: ActivityNotFoundException) {
                //   if chrome is not installed it ll redirect to the playStore
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.android.chrome&hl=en")))
            }

        } else {
            Toast.makeText(context, "Unavalaible", Toast.LENGTH_SHORT).show()
        }

    }
}