package com.bw.kf.park_fzy_kotlin.sign

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
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
    private var address: String = "天津市"
    private var time: String = ""

    private lateinit var signAdapter: SignAdapter

    override fun initView() {
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

    override fun initData() {

        viewModel.onSignListSuccess.observe(this){
            Log.i("TAGA", "initData: 签到记录: $it")
            val beanList = Gson().fromJson(it , SignBeanList::class.java)
            signAdapter.data.clear()
            signAdapter.data.addAll(beanList)
            signAdapter.notifyDataSetChanged()
        }
    }
}