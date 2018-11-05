package com.yc.betterlife.mvp.wechathot

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.basefrg.BaseFrgView

interface WechatHotView: BaseFrgView {
    fun showFrgs(frgs: ArrayList<RxFragment>, titles:ArrayList<String>)
}