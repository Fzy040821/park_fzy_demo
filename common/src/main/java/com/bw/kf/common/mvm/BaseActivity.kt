package com.bw.kf.common.mvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.TimeUtils
import java.lang.reflect.ParameterizedType
import java.text.SimpleDateFormat

abstract class BaseActivity<VDB: ViewDataBinding , VM: BaseViewModel>: AppCompatActivity() {
    private val type by lazy { (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }

    protected val dataBinding: VDB by lazy {
        val clazz = type[0] as Class<VDB>
        val method = clazz.getMethod("inflate" , LayoutInflater::class.java)
        method.invoke(null , layoutInflater) as VDB
    }

    protected val viewModel: VM by lazy {
        val clazz = type[1] as Class<VM>
        ViewModelProvider(this)[clazz]
    }

    open fun onError(t: Throwable){
        Log.i("TAGA", "onError: ${t.message}")
        t.printStackTrace()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dataBinding.root)
        viewModel.onFailed.observe(this , ::Error)
        initView()
        initData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    abstract fun initView()

    abstract fun initData()

    fun getTime(): String{
        val timm = System.currentTimeMillis()
        var tampTime = TimeUtils.millis2String(timm , SimpleDateFormat("yyyy-MM-dd"))
        tampTime+= "T"
        tampTime+= TimeUtils.millis2String(timm , SimpleDateFormat("hh:mm:ss"))
        tampTime+="Z"
        return tampTime
    }

}