package com.bw.kf.park_fzy_kotlin.news

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bw.kf.common.entity.NewsList
import com.bw.kf.common.mvm.BaseActivity
import com.bw.kf.common.net.Const
import com.bw.kf.park_fzy_kotlin.adapter.NewsAdapter
import com.bw.kf.park_fzy_kotlin.databinding.ActivityNewsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding , NewsViewModel>() {
    private lateinit var newsAdapter: NewsAdapter

    override fun initView() {
        dataBinding.newsHeader.headerTitles.text = "新闻公告"
        dataBinding.newsHeader.headerBack.visibility = View.VISIBLE
        dataBinding.newsHeader.headerAdd.visibility = View.VISIBLE
        dataBinding.newsHeader.headerBack.setOnClickListener { finish() }

        newsAdapter = NewsAdapter()
        dataBinding.newsRv.layoutManager = LinearLayoutManager(this)
        dataBinding.newsRv.adapter = newsAdapter

        dataBinding.newsHeader.headerAdd.setOnClickListener {
            val intent = Intent(this , AddNewsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun initData() {
        getNews()

        viewModel.onGetNewsSuccess.observe(this){
            Log.i("TAGA", "initData: 新闻数据获取: $it")
            newsAdapter.data.clear()
            val dataList = Gson().fromJson(it , NewsList::class.java)
            newsAdapter.data.addAll(dataList)
            newsAdapter.notifyDataSetChanged()
        }
    }

    private fun getNews(){
        val map = mutableMapOf<String , Int>()
        map[Const.PARAM_NEWS_PAGE] = 0
        val json = Gson().toJson(map)
        val body = RequestBody.create(Const.TYPE.toMediaTypeOrNull() , json)
        viewModel.getNews(body)
    }

    override fun onResume() {
        super.onResume()
        getNews()
    }

}