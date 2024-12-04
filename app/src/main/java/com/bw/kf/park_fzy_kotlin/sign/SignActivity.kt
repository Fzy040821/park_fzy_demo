package com.bw.kf.park_fzy_kotlin.sign

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.bw.kf.common.entity.SignBeanList
import com.bw.kf.common.mvm.BaseActivity
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.R
import com.bw.kf.park_fzy_kotlin.adapter.SignAdapter
import com.bw.kf.park_fzy_kotlin.databinding.ActivitySignBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/**
 * 签到
 * @author : FZY
 * @date : 2024/12/3
 */
@AndroidEntryPoint
class SignActivity : BaseActivity<ActivitySignBinding , SignViewModel>() {
    private var signId: Int = 0//0:签到 1:签退
    private var userId: Int = 0
    private var address: String = "杨柳青"
    private var time: String = ""

    private lateinit var signAdapter: SignAdapter

    override fun initView() {

        //签到签退
        signId = SPUtils.getInstance().getInt(Const.SIGN_ID)
        if(signId == 0){
            dataBinding.signBtn.text = "签到"
        }else{
            dataBinding.signBtn.text = "签退"
        }
        //点击效果
        dataBinding.signBtn.setOnClickListener {
            if(signId == 0){
                //签到
                startSign()
            }else{
                //签退
                endSign()
            }
        }

        dataBinding.signHeader.headerTitles.text = "签到"
        dataBinding.signHeader.headerBack.visibility =View.VISIBLE
        dataBinding.signHeader.headerBack.setOnClickListener { finish() }

        //签到记录
        signAdapter = SignAdapter()
        dataBinding.signListRv.layoutManager = LinearLayoutManager(this)
        dataBinding.signListRv.adapter = signAdapter
        //月份适配
        var adapter = ArrayAdapter(this , android.R.layout.simple_spinner_item ,resources.getStringArray(R.array.months) )
        dataBinding.signSpinner.adapter = adapter
        dataBinding.signSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var map = mutableMapOf<String , Int>()
                map[Const.PARAM_MONTH] = position +1
                var json = Gson().toJson(map)
                var body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
                viewModel.getSignList(body)//选择好月份后发送网络请求
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

    }

    /**
     * 签到
     */
    private fun startSign() {
        time =getTime()
        val map = mutableMapOf<String ,Any>()
        map[Const.USER_ID] = userId
        map[Const.SIGN_START_ADDRESS] = address
        map[Const.SIGN_END_ADDRESS] = address
        map[Const.SIGN_START_TIME] =time
        map[Const.SIGN_END_TIME] = time
        map[Const.SIGN_ID] = signId
        val json = Gson().toJson(map)
        var body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
        viewModel.signIn(body)
    }

    /**
     * 签退
     */
    private fun endSign(){
        time = getTime()
        val map = mutableMapOf<String , Any>()
        map[Const.USER_ID] = userId
        map[Const.SIGN_START_ADDRESS] = address
        map[Const.SIGN_END_ADDRESS] = address
        map[Const.SIGN_START_TIME] = time
        map[Const.SIGN_END_TIME] = time
        map[Const.SIGN_ID] = signId

        val json = Gson().toJson(map)
        val body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
        viewModel.signOut(body)
    }

    override fun initData() {

        userId = SPUtils.getInstance().getInt(Const.USER_ID)

        viewModel.onSignInSuccess.observe(this){
            ToastUtils.showLong("签到成功")
            dataBinding.signBtn.text = "签退"
            signId = 1
            SPUtils.getInstance().put(Const.SIGN_ID , signId)
        }

        viewModel.onSignOutSuccess.observe(this){
            ToastUtils.showLong("签退成功")
            dataBinding.signBtn.text = "签到"
            signId = 0
            SPUtils.getInstance().put(Const.SIGN_ID , signId)
            //签退成功再次请求列表
            val map = mutableMapOf<String , Int>()
            map[Const.PARAM_MONTH] = dataBinding.signSpinner.selectedItemPosition+1
            val json = Gson().toJson(map)
            val body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
            viewModel.getSignList(body)
        }

        viewModel.onSignListSuccess.observe(this){
            Log.i("TAGA", "initData: 签到记录: $it")
            val beanList = Gson().fromJson(it , SignBeanList::class.java)
            signAdapter.data.clear()
            signAdapter.data.addAll(beanList)
            signAdapter.notifyDataSetChanged()
        }
    }
}