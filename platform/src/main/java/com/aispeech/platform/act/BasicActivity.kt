package com.aispeech.platform.act

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

import java.lang.reflect.ParameterizedType

abstract class BasicActivity<T : ViewBinding> : AppCompatActivity() {

    // 定义一个可空的 ViewBinding 属性
    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化 ViewBinding
        _binding = getViewBinding()
        setContentView(binding.root)

        initData()
        initView()
    }

    // 通过反射获取 ViewBinding 的实例
    @Suppress("UNCHECKED_CAST")
    private fun getViewBinding(): T {
        val superclass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        val inflateMethod = superclass.getMethod("inflate", LayoutInflater::class.java)
        return inflateMethod.invoke(null, layoutInflater) as T
    }

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}