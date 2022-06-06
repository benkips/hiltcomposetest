package com.mabnets.hiltcomposetest.Utils

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDeepLinkBuilder
import java.io.IOException
import java.net.URL



fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}



fun getBitmapfromUrl(imageUrl: String?): Bitmap? {
    return try {
        val url = URL(imageUrl)
        BitmapFactory.decodeStream(url.openConnection().getInputStream())
    } catch (e: IOException) {
        System.out.println(e)
        null
    }
}

sealed class UIEvent {
    data class ShowSnackbar(val message: String) : UIEvent()
}


