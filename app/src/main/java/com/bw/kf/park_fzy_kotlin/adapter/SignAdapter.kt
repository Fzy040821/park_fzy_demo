package com.bw.kf.park_fzy_kotlin.adapter

import com.bw.kf.common.entity.SignEntity
import com.bw.kf.park_fzy_kotlin.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.text.SimpleDateFormat
import java.util.Date

class SignAdapter: BaseQuickAdapter<SignEntity , BaseViewHolder>(R.layout.item_sign_list) {
    override fun convert(holder: BaseViewHolder, item: SignEntity) {
        //设置地址
        holder.setText(R.id.sign_in_address_start_now_tv , item.startAddress)
        holder.setText(R.id.sign_out_address_start_now_tv , item.endAddress)

        //设置时间
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateStart = Date(item.startTime)
        val startTime = sdf.format(dateStart)
        holder.setText(R.id.sign_in_time_start_now_tv , startTime)//签到时间

        val dateEnd = Date(item.endTime)
        val endTime = sdf.format(dateEnd)
        holder.setText(R.id.sign_out_time_start_now_tv , endTime)
    }
}