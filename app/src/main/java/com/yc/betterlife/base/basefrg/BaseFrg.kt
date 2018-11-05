package com.yc.betterlife.base.basefrg

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.R
import com.yc.betterlife.mvp.MainAct
import io.reactivex.Observable
import kotlinx.android.synthetic.main.base_error_layout.*
import kotlinx.android.synthetic.main.frg_base.*
import kotlinx.android.synthetic.main.title_layout.*
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

abstract class BaseFrg<V : BaseFrgView, T : BasePresenter<V>> : RxFragment(), BaseFrgView
        , SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {
    abstract val resId: Int
    lateinit var presenter: T
    lateinit var act: MainAct
    var rootView: View? = null

    var isInit = false

    var errorView: View? = null

    open var swipe: SwipeRefreshLayout? = null


    var isRegister = false

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        act = getActivity() as MainAct
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = LayoutInflater.from(act).inflate(R.layout.frg_base, container, false)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isInit) {
            return
        }

        isInit = true
        var childView = LayoutInflater.from(act).inflate(resId, null)
        lv_container.addView(childView)
        lv_title.visibility = View.GONE
        errorView = LayoutInflater.from(act).inflate(R.layout.base_error_layout, null)
        lv_error.addView(errorView)
        tv_title.text = if (TextUtils.isEmpty(getTitle())) "" else getTitle()
        if (isRegister && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        presenter = initPresenter()
        presenter.attachView(this)
        initView()
        bindLisenter()
        bindToLife()
        getNetDataOnce()
    }

    open fun getTitle(): String {
        return ""
    }

    abstract fun initView()

    open fun bindLisenter() {
        tv_refresh.setOnClickListener { refreshData() }
        swipe?.setOnRefreshListener(this)
    }

    abstract fun initPresenter(): T

    open fun initNetworkData(isRefresh: Boolean) {

    }


    open fun getNetDataOnce() {
        initNetworkData(true)
    }


    override fun showMsg(msg: String) {
        ToastUtils.showShort(msg)
    }

    fun bindToLife() {
        presenter.lifecycleOwner = this
        lifecycle.addObserver(presenter)
    }

    override fun onResume() {
        super.onResume()
        presenter?.attach(this as V)
        hideInput()
    }

    override fun hideInput() {
        try {
            val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(act.window.decorView.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun showInput(et: EditText) {
        try {
            et.setFocusable(true)
            et.setFocusableInTouchMode(true)
            et.requestFocus()
            // 显示软键盘
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .compose(bindToLifecycle())
                    .subscribe { aLong ->
                        try {
                            val imm = act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.showSoftInput(et, 0)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onDestroyView() {
        if (isRegister && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroyView()
    }

    override fun showLoading(isShow: Boolean) {
        lv_loading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showError(isShow: Boolean) {
        errorView?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun refreshData() {
        initNetworkData(true)
    }

    override fun onRefresh() {
        initNetworkData(true)
    }

    override fun onLoadMoreRequested() {
        initNetworkData(false)
    }

    override fun startRefresh() {
        swipe?.isRefreshing = true
    }

    override fun stopRefresh() {
        swipe?.isRefreshing = false
    }

    override fun showLoadFailed() {

    }

    override fun showNoMoreData() {

    }

    override fun clearDatas() {

    }

}
