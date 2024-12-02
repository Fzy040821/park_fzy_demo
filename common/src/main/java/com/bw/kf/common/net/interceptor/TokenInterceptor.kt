package com.bw.kf.common.net.interceptor

import com.blankj.utilcode.util.SPUtils
import com.bw.kf.common.net.Const
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            Const.PARAM_TOKEN,
            SPUtils.getInstance().getString(Const.PARAM_TOKEN)
        ).build()
        return chain.proceed(request)
    }
}