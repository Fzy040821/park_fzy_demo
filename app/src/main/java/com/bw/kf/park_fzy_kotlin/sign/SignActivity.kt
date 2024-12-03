package com.bw.kf.park_fzy_kotlin.sign

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPUtils
import com.bw.kf.common.mvm.BaseActivity
import com.bw.kf.common.net.Const

import com.bw.kf.park_fzy_kotlin.R
import com.bw.kf.park_fzy_kotlin.databinding.ActivitySignBinding

/**
 * 签到
 * @author : FZY
 * @date : 2024/12/3
 */
class SignActivity : BaseActivity<ActivitySignBinding , SignViewModel>() {
    private var signId: Int = 0//0:签到 1:签退

    override fun initView() {
        dataBinding.signHeader.headerTitles.text = "签到"
        dataBinding.signHeader.headerBack.visibility =View.VISIBLE
        dataBinding.signHeader.headerBack.setOnClickListener { finish() }

        signId = SPUtils.getInstance().getInt(Const.SIGN_ID)

        if(signId == 0){
            dataBinding.signBtn.text = "签退"
        }else{
            dataBinding.signBtn.text = "签到"
        }
        dataBinding.signBtn.setOnClickListener {
            if(signId == 0){
                SPUtils.getInstance().put(Const.SIGN_ID , 1)
                dataBinding.signBtn.text = "签退"
                //TODO 发起签到网络请求
            }else{
                SPUtils.getInstance().put(Const.SIGN_ID , 0)
                dataBinding.signBtn.text = "签到"
                //TODO 发起签退网络请求
            }
        }

    }

    override fun initData() {
    }
}