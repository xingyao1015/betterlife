package com.yc.betterlife.mvp.wechathot.wehcatab

import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.entity.WechatItemResulListBean

interface WechatHotTabView: BaseFrgView {
    fun showData(data:ArrayList<WechatItemResulListBean>)
}