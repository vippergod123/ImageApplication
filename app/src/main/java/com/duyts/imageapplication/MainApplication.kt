package com.duyts.imageapplication

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.*


class MainApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        application = this

    }

    companion object {
        private lateinit var application: Application
        fun getApplication(): Application {
            return application
        }
    }
}