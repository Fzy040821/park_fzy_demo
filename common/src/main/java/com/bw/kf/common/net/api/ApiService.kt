package com.bw.kf.common.net.api

import com.bw.kf.common.entity.BaseEntity
import com.bw.kf.common.entity.SignEntity
import com.bw.kf.common.net.Const
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //获取签到列表
    @POST(Const.URL_GET_SIGN_LIST)
    suspend fun getSignList(@Body body:RequestBody): BaseEntity<String>

    //签到
    @POST(Const.URL_SIGN_IN)
    suspend fun signIn(@Body body: RequestBody): BaseEntity<SignEntity>

    //签退
    @POST(Const.URL_SIGN_OUT)
    suspend fun signOut(@Body body: RequestBody): BaseEntity<SignEntity>
}