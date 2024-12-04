package com.bw.kf.park_fzy_kotlin.news

import androidx.lifecycle.MutableLiveData
import com.bw.kf.common.mvm.BaseViewModel
import com.bw.kf.common.net.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val apiService: ApiService
): BaseViewModel() {

    val onGetNewsSuccess by lazy { MutableLiveData<String>() }

    val onAddNewsSuccess by lazy { MutableLiveData<String>() }

    fun getNews(body: RequestBody){
        executeRequest(
            {apiService.getNews(body)},
            onGetNewsSuccess::postValue
        )
    }

    fun addNews(body: RequestBody){
        executeRequest(
            {apiService.addNews(body)},
            onAddNewsSuccess::postValue
        )
    }

}