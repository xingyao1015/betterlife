package com.yc.betterlife.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.yc.betterlife.R
import com.yc.betterlife.entity.CookListListBean

class CookListAdapter(datas: ArrayList<CookListListBean>)
    : BaseQuickAdapter<CookListListBean, BaseViewHolder>(R.layout.item_cook_list, datas) {
    override fun convert(helper: BaseViewHolder, item: CookListListBean) {
        helper.setText(R.id.tv_title, item.name)
        var iv = helper.getView<SimpleDraweeView>(R.id.iv)
        iv.setImageURI(item.thumbnail)
    }
}