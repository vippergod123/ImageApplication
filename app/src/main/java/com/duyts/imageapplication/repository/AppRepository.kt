package com.duyts.imageapplication.repository

import com.duyts.imageapplication.network.AppRetrofit

class AppRepository {
    suspend fun getProfile() = AppRetrofit.profile.getProfile()
}