package com.yc.betterlife.mvp

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.baseact.BaseActView
import com.yc.betterlife.base.basefrg.BaseFrgView

interface MainActView: BaseFrgView {
    fun showFrgs(frgs:ArrayList<RxFragment>)
}