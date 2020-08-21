package com.incred.photogallery.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}

fun Context.isNetworkConnected():Boolean{
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}
