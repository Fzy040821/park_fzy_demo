package com.bw.kf.common.mvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VDB: ViewDataBinding , VM: BaseViewModel>: Fragment() {
    private val type by lazy { (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }

    protected val dataBinding: VDB by lazy {
        val clazz = type[0] as Class<VDB>
        val method = clazz.getMethod("inflate" , LayoutInflater::class.java)
        method.invoke(null , layoutInflater)as VDB
    }

    protected val viewModel: VM by lazy {
        val clazz = type[1] as Class<VM>
        ViewModelProvider(this)[clazz]
    }

    open fun onError(t: Throwable){
        ToastUtils.showLong(t.message)
        t.printStackTrace()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = dataBinding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onFailed.observe(viewLifecycleOwner , ::Error)
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()
}