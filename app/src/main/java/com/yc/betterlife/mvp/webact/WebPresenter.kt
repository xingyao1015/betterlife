package com.yc.betterlife.mvp.webact

import com.yc.betterlife.base.baseact.BaseActPresenter
import com.yc.betterlife.base.baseact.BaseActView

class WebPresenter: BaseActPresenter<WebFrgView>() {
    lateinit var v:WebFrgView
    override fun attachView(v: BaseActView) {
        this.v = v as WebFrgView
    }
}