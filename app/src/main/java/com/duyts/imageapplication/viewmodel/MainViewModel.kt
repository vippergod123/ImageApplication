package com.duyts.imageapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duyts.imageapplication.MainApplication
import com.duyts.imageapplication.adapter.DataResponse
import com.duyts.imageapplication.repository.AppRepository
import com.duyts.imageapplication.util.Event
import com.duyts.imageapplication.util.Resource
import com.duyts.imageapplication.util.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel (  private val appRepository: AppRepository): ViewModel() {

    private val _profile = MutableLiveData<Event<Resource<DataResponse>>>()
    val profile: LiveData<Event<Resource<DataResponse>>> = _profile

    fun getProfile() = viewModelScope.launch {
        getProfileInternal()
    }


    private suspend fun getProfileInternal() {
        _profile.postValue(Event(Resource.Loading()))
        try {
            if (Utils.hasInternetConnection(MainApplication.getApplication())) {
                val response = appRepository.getProfile()
//                        _profile.postValue(handlePicsResponse(response))
                _profile.postValue(handleResponse(response))
            } else {
                _profile.postValue(Event(Resource.Failed("No internet connection")))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _profile.postValue(Event(Resource.Failed("Network error")))
                }
                else -> {
                    _profile.postValue(Event(Resource.Failed("Network error")))
                }
            }
        }
    }


    private fun handleResponse(response: Response<DataResponse>): Event<Resource<DataResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Failed(response.message()))
    }
}