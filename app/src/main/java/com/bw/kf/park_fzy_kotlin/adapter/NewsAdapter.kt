package com.bw.kf.park_fzy_kotlin.adapter

import com.bw.kf.common.entity.NewsEntity
import com.bw.kf.park_fzy_kotlin.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class NewsAdapter: BaseQuickAdapter<NewsEntity, BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: NewsEntity) {
        holder.setText(R.id.item_news_titles , item.newsTitle)
        holder.setText(R.id.item_news_content , item.newsValue)
        holder.setText(R.id.item_news_send_time , item.newsTime)
    }
}