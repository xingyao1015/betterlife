package com.yc.betterlife.mvp.normalmanager

import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter

class NormalManagerPresenter : BasePresenter<NormalManagerView>() {
    var v: NormalManagerView? = null
    override fun attachView(v: BaseFrgView) {
        this.v = v as NormalManagerView
    }

}