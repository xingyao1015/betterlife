package com.yc.betterlife.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.facebook.drawee.view.SimpleDraweeView
import com.yc.betterlife.R
import com.yc.betterlife.entity.CookBookDetailRecipeMethod

class CookBookDetailAdapter(datas: ArrayList<CookBookDetailRecipeMethod>) : BaseQuickAdapter<CookBookDetailRecipeMethod, BaseViewHolder>(R.layout.item_cookbook_detail, datas) {
    override fun convert(helper: BaseViewHolder, item: CookBookDetailRecipeMethod) {
        helper.getView<SimpleDraweeView>(R.id.iv).setImageURI(item.img)
        helper.setText(R.id.tv, item.step)
    }
}