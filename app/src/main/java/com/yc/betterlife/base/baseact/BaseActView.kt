package com.yc.betterlife.base.baseact

import android.widget.EditText

interface BaseActView {
    fun showMsg(msg:String)

    fun hideInput()

    fun back()

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