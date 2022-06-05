package com.sekvenie

import android.app.Application
import androidx.navigation.NavController
import androidx.navigation.Navigation
import coil.ImageLoader
import coil.imageLoader

class App: Application() {

    companion object {
        var navController: NavController? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}