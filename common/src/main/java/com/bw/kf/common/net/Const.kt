package com.bw.kf.common.net

class Const {
    companion object{
        const val BASE_URL = "http://10.161.9.80:7035/"

        //登录
        const val URL_LOGIN = "/sysUser/loginUser"
        const val PARAM_NAME_LOGIN = "uName"
        const val PARAM_PWD_LOGIN = "uPwd"
        const val TIME = "time"
        const val TYPE = "application/json;charset=utf-8"
        const val PARAM_TOKEN = "token"
        const val HOME = "home"
        const val PARAM_SIGN = "sign"
        const val USER_ID = "userId"

        //签到
        const val URL_SIGN_IN = "/SysSign/startSign"//签到
        const val URL_SIGN_OUT = "/SysSign/endSign"//签退
        const val URL_GET_SIGN_LIST = "/SysSign/selMonth"//查询月签到记录
        const val PARAM_MONTH = "month"
        const val SIGN_ID = "signId"
        const val SIGN_END_ADDRESS = "endAddress"
        const val SIGN_END_TIME = "endTime"
        const val SIGN_START_ADDRESS = "startAddress"
        const val SIGN_START_TIME = "startTime"

        //新闻
        const val URL_GET_NEWS = "/sysNews/selAllNews"
        const val PARAM_NEWS_PAGE = "page"
        //添加新闻
        const val URL_ADD_NEWS = "/sysNews/addNews"
        const val NEWS_TIME = "newsTime"
        const val NEWS_TITLE = "newsTitle"
        const val NEWS_VALUE = "newsValue"


    }
}