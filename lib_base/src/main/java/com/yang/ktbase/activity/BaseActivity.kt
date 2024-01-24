package com.yang.ktbase.activity

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yang.ktbase.net.ReqOwner
import com.yang.ktbase.util.inflateBindingWithGeneric

/**
 * UI相关基类，
 * 实现viewBind功能
 * ...其他UI相关的公共逻辑也可以在这里
 *
 * @param B: ViewBinding
 * @property mBinding B
 */
abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), ReqOwner {

    /**
     * viewBind
     */
    protected val mBinding by lazy {
        inflateBindingWithGeneric<B>(layoutInflater)
    }

    /**
     * 网络请求加载弹窗
     */
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView(savedInstanceState)
    }

    /**
     * UI初始化/绑定相关
     */
    abstract fun initView(savedInstanceState: Bundle?)


    /**
     * 展示一般加载中弹窗
     */
    override fun showLoading(msg: String) {
        progressDialog.show()
        progressDialog.setTitle(msg)
    }

    /**
     * 关闭一般加载中弹窗
     */
    override fun hideLoading() {
        progressDialog.takeIf { it.isShowing }?.dismiss()
    }


}