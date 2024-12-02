package com.bw.kf.park_fzy_kotlin.login


import android.content.Intent
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.bw.kf.common.entity.LoginEntity
import com.bw.kf.common.mvm.BaseActivity
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.MainActivity
import com.bw.kf.park_fzy_kotlin.databinding.ActivityLoginBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding , LoginViewModel>() {

    override fun initView() {

        dataBinding.loginBtn.setOnClickListener {
            var num = dataBinding.loginNum.text.toString()
            var pwd = dataBinding.loginPwd.text.toString()
            var timeStr = (System.currentTimeMillis()/1000).toString()

            val map = mutableMapOf<String , String>()
            map[Const.PARAM_NAME_LOGIN] = num
            map[Const.PARAM_PWD_LOGIN] = pwd
            map[Const.TIME] = "1733101886"
            map[Const.PARAM_SIGN] = "45b84b76239bf31f6ab7602ac89f0156"
            val json = Gson().toJson(map)
            var body = RequestBody.create("application/json;charset=utf-8".toMediaTypeOrNull(),json)

            viewModel.login(body)
            println("发起登录请求")
        }

        dataBinding.loginHeader.headerTitles.text = "登录"
        dataBinding.loginHeader.headerBack.visibility = View.GONE
    }

    override fun initData() {
        viewModel.onLoginSuccess.observe(this){
           try {
               val bean = Gson().fromJson(it , LoginEntity::class.java)
               ToastUtils.showLong("欢迎回来,${bean.uName}")
               SPUtils.getInstance().put(Const.PARAM_TOKEN, bean.token)
               SPUtils.getInstance().put(Const.PARAM_NAME_LOGIN, bean.uName)
               SPUtils.getInstance().put(Const.PARAM_PWD_LOGIN, bean.uPwd)
               SPUtils.getInstance().put(Const.USER_ID, bean.uId)
               Log.i("TAGA", "initData: ${SPUtils.getInstance().getString(Const.PARAM_TOKEN)}")
               startActivity(Intent(this , MainActivity::class.java))
           }catch (e: Exception){
               e.printStackTrace()
               ToastUtils.showLong("登陆失败${e.message}")
           }
        }
        viewModel.onFailed.observe(this){
            ToastUtils.showLong(it.message)
        }
    }

}