package com.yc.betterlife.entity

data class WechatItem(
        var msg: String,
        var retCode: Int,
        var result: WechatItemResultBean
)

data class WechatItemResultBean(
        var curPage:Int,
        var total:Int,
        var list:ArrayList<WechatItemResulListBean>? = null
)

data class WechatItemResulListBean(
        var cid:Int,
        var id:String,
        var pubTime:String,
        var sourceUrl:String,
        var subTitle:String? = null,
        var thumbnails:String? = null,
        var title:String
)