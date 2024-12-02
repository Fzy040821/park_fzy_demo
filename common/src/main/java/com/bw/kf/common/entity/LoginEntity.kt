package com.bw.kf.common.entity

data class LoginEntity(
    val dept_id: Int,
    val pId: Int,
    val time: String,
    val token: String,
    val uId: Int,
    val uName: String,
    val uPwd: String
)
