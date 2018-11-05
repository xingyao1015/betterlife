package com.yc.betterlife.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.yc.betterlife.R
import com.yc.betterlife.entity.WechatItemResulListBean
import com.yc.betterlife.mvp.wechathot.wehcatab.WechatTabFrg

class WechatItemAdapter(data: ArrayList<WechatItemResulListBean>)
    : BaseQuickAdapter<WechatItemResulListBean, BaseViewHolder>
(R.layout.item_wechat_tab, data) {
    override fun convert(helper: BaseViewHolder, item: WechatItemResulListBean) {
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.tv_sub_title, item.subTitle)
                .setText(R.id.tv_time, item.pubTime)
                .setVisible(R.id.iv, item.thumbnails != null)
        if (item?.thumbnails != null) {
            var iv = helper.getView<SimpleDraweeView>(R.id.iv)
            iv.setImageURI(item.thumbnails)
        }
    }
}