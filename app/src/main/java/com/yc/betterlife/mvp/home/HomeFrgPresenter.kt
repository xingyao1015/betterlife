package com.yc.betterlife.mvp.home

import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter

class HomeFrgPresenter : BasePresenter<HomeFrgView>() {
    lateinit var v:HomeFrgView
    override fun attachView(v: BaseFrgView) {
        this.v = v as HomeFrgView
    }
}