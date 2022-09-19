package com.ahmed.marvelapp.utilities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Utils {

    fun navigateUp(view: View) {
        view.findNavController().navigateUp()
    }

    fun share(activity: Activity, url: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        activity.startActivity(Intent.createChooser(sendIntent, null))
    }

    fun openUrl(activity: Activity, url: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(urlIntent)
    }

    companion object {
        val currentTimeStamp = Timestamp(System.currentTimeMillis()).time.toString()

        fun hash(): String {
            val input = "$currentTimeStamp${Constants.PRIVATE_API_KEY}${Constants.PUBLIC_API_KEY}"
            val md = MessageDigest.getInstance(Constants.HASH_ALGORITHM)
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}