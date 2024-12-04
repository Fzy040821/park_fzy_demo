package com.bw.kf.park_fzy_kotlin.sign

import androidx.lifecycle.MutableLiveData
import com.bw.kf.common.mvm.BaseViewModel
import com.bw.kf.common.net.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val apiService: ApiService
): BaseViewModel() {

    val onSignInSuccess by lazy { MutableLiveData<String>() }
    val onSignOutSuccess by lazy { MutableLiveData<String>() }
    val onSignListSuccess by lazy { MutableLiveData<String>() }

    fun signIn(body: RequestBody){
        executeRequest(
            {apiService.signIn(body)},
            onSignInSuccess::postValue
        )
    }

    fun signOut(body: RequestBody){
        executeRequest(
            {apiService.signOut(body)},
            onSignOutSuccess::postValue
        )
    }

    fun getSignList(body: RequestBody){
        executeRequest(
            {apiService.getSignList(body)},
            onSignListSuccess::postValue
        )
    }

}