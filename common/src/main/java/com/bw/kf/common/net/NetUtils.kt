package com.bw.kf.common.net

import com.blankj.utilcode.BuildConfig
import com.bw.kf.common.net.interceptor.SignInterceptor
import com.bw.kf.common.net.interceptor.TokenInterceptor
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author : FZY
 * @param: 佛祖保佑  永无Bug
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . ___
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *
 *                     佛祖保佑        永无BUG
 */

/**
 * sign请求工具
 */
private val clientSign by lazy {
    OkHttpClient.Builder()
        .readTimeout(30 , TimeUnit.SECONDS)
        .connectTimeout(30 , TimeUnit.SECONDS)
        .writeTimeout(30 ,TimeUnit.SECONDS)
        .apply {
            //添加日志拦截器
            if(BuildConfig.DEBUG){
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }
//        .addInterceptor(SignInterceptor())
        .build()
}

val retrofitSign: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .client(clientSign)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
inline fun <reified T> getServiceSign(): T = retrofitSign.create(T::class.java)


/**
 * token请求工具
 */
private val clientToken by lazy {
    OkHttpClient.Builder()
        .readTimeout(30 , TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30 , TimeUnit.SECONDS)
        .apply {
            if(BuildConfig.DEBUG){
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }
        .addInterceptor(TokenInterceptor())
        .build()
}

val retrofitToken: Retrofit by lazy {
    Retrofit.Builder()
        .client(clientToken)
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

inline fun <reified T> getServiceToken(): T = retrofitToken.create(T::class.java)