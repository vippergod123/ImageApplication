package com.duyts.imageapplication.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.time.Year
import java.time.YearMonth
import java.util.*

object Utils {
    fun hasInternetConnection(application: Application): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    @SuppressLint("SimpleDateFormat")
    fun milisecondToDobFormat(time: Int): String {
        val formatter = SimpleDateFormat("MMM yyyy")
        val calendar: Calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        calendar.timeInMillis = time.toLong()
        return String.format(
            "%s, %s years old",
            formatter.format(calendar.time),
            currentYear - calendar.get(Calendar.YEAR)
        )
    }
}
