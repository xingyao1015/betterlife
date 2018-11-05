package com.yc.betterlife.entity

data class WechatTabs(
        var msg: String,
        var retCode: Int,
        var result: List<WechatTabsResultBean>? = null
)

data class WechatTabsResultBean(
        var cid: Int,
        var name: String
)
