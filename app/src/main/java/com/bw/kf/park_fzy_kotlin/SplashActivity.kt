package com.bw.kf.park_fzy_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.login.LoginActivity
import com.gyf.immersionbar.ImmersionBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ImmersionBar.with(this)
            .transparentBar()
            .init()

        Thread(){
            run {
                try {
                    Thread.sleep(2000)
                }catch (e: Exception){
                    e.printStackTrace()
                    println(e.message+ "出错了")
                }
                runOnUiThread{
                    if(SPUtils.getInstance().getString(Const.PARAM_TOKEN).isEmpty()){
                        val intent = Intent(this@SplashActivity , LoginActivity::class.java)
                        startActivity(intent)
                    }else{
                        val intent = Intent(this@SplashActivity , MainActivity::class.java)
                        Log.i("TAGA", "onCreate: ${SPUtils.getInstance().getString(Const.PARAM_TOKEN)}")
                        ToastUtils.showLong("欢迎来到bw园区 , ${SPUtils.getInstance().getString(Const.PARAM_NAME_LOGIN)}")
                        startActivity(intent)
                    }
                }
                finish()
            }
        }.start()
    }
}