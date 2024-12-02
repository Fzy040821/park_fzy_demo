package com.bw.kf.common.net.interceptor

import android.util.Log
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.SPUtils
import com.bw.kf.common.net.Const
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONObject

class SignInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()

        //拿到请求体
        val body = request.body
        val buffer = Buffer()
        body?.writeTo(buffer)
        val bodyStr = buffer.toString()
        Log.i("TAGA", "intercept: $bodyStr")
        buffer.close()//关闭流

        //截取到json数据
        val jsonStr = bodyStr.substring(bodyStr.indexOf("{") , bodyStr.lastIndexOf("}")+1)
        Log.i("TAGA", "intercept: $jsonStr")//打印切割后的请求体

        //获取键值对
        val sb =StringBuffer()
        val jsonObj = JSONObject(jsonStr)
        val keys =jsonObj.keys()
        while (keys.hasNext()){
            val key =keys.next()
            val value =jsonObj.getString(key)
            sb.append(value)//拼接签名
        }

        //拼接密钥
        sb.append("tamboo")
        Log.i("TAGA", "intercept: ${sb.toString()}")//打印拼接后的请求体

        //MD5加密
        val sign = EncryptUtils.encryptMD5ToString(sb.toString()).toLowerCase()

        //完成新的请求体
        jsonObj.put("sing" , sign)
        Log.i("TAGA", "intercept--->: $sign")//加密后的请求体
        SPUtils.getInstance().put(Const.PARAM_SIGN , sign)
        val requestBody = RequestBody.create(
            "application/json;charset=utf-8".toMediaTypeOrNull(),
            jsonObj.toString()
        )
        builder.post(requestBody)
        request = builder.build()
        return chain.proceed(request)
    }
}