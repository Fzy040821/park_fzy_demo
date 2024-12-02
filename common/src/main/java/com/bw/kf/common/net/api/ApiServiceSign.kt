package com.bw.kf.common.net.api

import com.bw.kf.common.entity.BaseEntity
import com.bw.kf.common.entity.LoginEntity
import com.bw.kf.common.net.Const
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceSign {

    @POST(Const.URL_LOGIN)
    suspend fun login(@Body body: RequestBody): BaseEntity<LoginEntity>
}