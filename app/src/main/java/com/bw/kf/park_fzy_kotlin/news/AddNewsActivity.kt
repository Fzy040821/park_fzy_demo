package com.bw.kf.park_fzy_kotlin.news


import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.bw.kf.common.mvm.BaseActivity
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.databinding.ActivityAddNewsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

@AndroidEntryPoint
class AddNewsActivity : BaseActivity<ActivityAddNewsBinding , NewsViewModel>() {
    override fun initView() {
        dataBinding.addNewsHeader.headerBack.visibility = View.VISIBLE
        dataBinding.addNewsHeader.headerBack.setOnClickListener { finish() }
        dataBinding.addNewsHeader.headerTitles.text = "添加新闻"
        dataBinding.commitNewsBtn.setOnClickListener {
            sendNews()
        }
    }

    override fun initData() {
        viewModel.onAddNewsSuccess.observe(this){
            ToastUtils.showLong("添加成功")
            dataBinding.addNewsTitle.text.clear()
            dataBinding.addNewsContent.text.clear()
            finish()
        }
    }

    private fun sendNews(){
        val title = dataBinding.addNewsTitle.text.toString()
        val content = dataBinding.addNewsContent.text.toString()
        val time = getTime()
        val userId = SPUtils.getInstance().getInt(Const.USER_ID)

        val map = mutableMapOf<String , Any>()
        map[Const.NEWS_TITLE] = title
        map[Const.USER_ID] = userId
        map[Const.NEWS_TIME] = time
        map[Const.NEWS_VALUE] = content

        val json = Gson().toJson(map)
        val body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
        viewModel.addNews(body)
    }
}