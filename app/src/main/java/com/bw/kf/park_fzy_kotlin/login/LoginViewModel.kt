package com.bw.kf.park_fzy_kotlin.login

import androidx.lifecycle.MutableLiveData
import com.bw.kf.common.mvm.BaseViewModel
import com.bw.kf.common.net.api.ApiServiceSign
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * 登录数据请求
 * @author : FZY
 * @Date: 2024/11/30 10:58
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val service: ApiServiceSign
): BaseViewModel() {

    val onLoginSuccess by lazy { MutableLiveData<String>() }

    fun login(body: RequestBody){
        executeRequest(
            {service.login(body)},
            onLoginSuccess::postValue
        )
    }
}