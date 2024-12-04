package com.bw.kf.common.entity

class SignBeanList: ArrayList<SignEntity>()
data class SignEntity(
    val endAddress: String,
    val endTime: Long,
    val signId: Int,
    val startAddress: String,
    val startTime: Long,
    val userId: Int
)