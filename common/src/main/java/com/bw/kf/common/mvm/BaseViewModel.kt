package com.bw.kf.common.mvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.SPUtils
import com.bw.kf.common.entity.BaseEntity
import com.bw.kf.common.net.Const
import kotlinx.coroutines.launch
import java.lang.RuntimeException

open class BaseViewModel: ViewModel() {

    //创建所有的错误信息
    val onFailed by lazy { MutableLiveData<Throwable>() }

    //发起网络请求方法
    fun <D> executeRequest(
        s: suspend ()->BaseEntity<D>,
        success: (String)->Unit,
        failed: (Throwable)->Unit = onFailed::postValue,
        okCode: Int = 200
    ){
        viewModelScope.launch {
            //使用协程发起网络请求
            try {
                val baseEntity = s.invoke()
                SPUtils.getInstance().put(Const.HOME , baseEntity.home)
                Log.i("TAGA", "executeRequestValue: ${baseEntity.values}")
                //判断请求码
                if(baseEntity.statuesCode.toInt() == okCode){
                    success(baseEntity.values)
                }else{
                    failed(RuntimeException(baseEntity.msg))
                }
            }catch (e: Exception){
                failed(e)
            }
        }
    }
}