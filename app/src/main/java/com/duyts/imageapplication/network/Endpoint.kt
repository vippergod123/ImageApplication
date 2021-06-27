package com.duyts.imageapplication.network

import com.duyts.imageapplication.adapter.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {
    @GET("longnguyen2605/vulcan_labs_assignment_1/main/db.json")
    suspend fun getProfile(): Response<DataResponse>
}