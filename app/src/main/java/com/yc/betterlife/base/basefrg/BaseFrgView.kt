package com.yc.betterlife.base.basefrg

import android.widget.EditText

interface BaseFrgView {
    fun showMsg(msg:String)

    fun hideInput()

    fun showInput(editText: EditText)

    fun showLoading(isShow:Boolean)

    fun showError(isShow: Boolean)

    fun refreshData()

    fun startRefresh()

    fun stopRefresh()

    fun showNoMoreData()

    fun showLoadFailed()

    fun clearDatas()

}