package com.yc.betterlife.mvp.normalmanager

import com.yc.betterlife.R
import com.yc.betterlife.base.basefrg.BaseFrg

class NormalManagerFrg : BaseFrg<NormalManagerView, NormalManagerPresenter>() ,NormalManagerView{
    var mPresenter = NormalManagerPresenter()

    override val resId: Int = R.layout.frg_normalmanager

    override fun initView() {

    }

    override fun initPresenter(): NormalManagerPresenter {
        return mPresenter
    }

    override fun initNetworkData(isRefresh: Boolean) {
    }
}