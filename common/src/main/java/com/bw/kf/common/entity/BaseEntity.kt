package com.bw.kf.common.entity

data class BaseEntity<D>(
    val msg: String,
    val statuesCode: String,
    val values: String,
    val home : String = "http://10.161.9.80:7035/index.html"
)
