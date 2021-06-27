package com.duyts.imageapplication.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duyts.imageapplication.adapter.DataResponse
import com.duyts.imageapplication.repository.AppRepository

class ViewModelProviderFactory(
        val app: Application,
        val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(appRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}