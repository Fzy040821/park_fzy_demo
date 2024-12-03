package com.bw.kf.park_fzy_kotlin

import android.app.Application
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        QbSdk.initX5Environment(this , object : PreInitCallback{
            override fun onCoreInitFinished() {
                //X5内核初始化完成
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * @param isX5 是否使用X5内核
             */
            override fun onViewInitFinished(p0: Boolean) {}

        })
    }
}