package com.yc.betterlife.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yc.betterlife.R
import com.yc.betterlife.entity.CookTypesChild
import com.yc.betterlife.entity.CookTypesChildChild
import com.yc.betterlife.mvp.cookbook.CookBookFrg

class CookTypeAdapter(val frg: CookBookFrg, datas: ArrayList<MultiItemEntity>) :
        BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(datas!!) {
    init {
        addItemType(TYPE_LEVEL_0, R.layout.item_cook_types_1)
        addItemType(TYPE_LEVEL_1, R.layout.item_cook_types_2)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity?) {
        when (helper.itemViewType) {
            TYPE_LEVEL_0 -> {
                val data1 = item as CookTypesChild
                helper.setText(R.id.tv_title, data1.categoryInfo.name)

                helper.itemView.setOnClickListener {
                    val pos = helper.adapterPosition
                    if (data1.isExpanded) {
                        collapse(pos, true);
                    } else {
                        expand(pos, true)
                    }
                }
            }
            TYPE_LEVEL_1 -> {
                val data2 = item as CookTypesChildChild
                var isChecked = frg.mPresenter.position == helper.adapterPosition
                helper.setText(R.id.tv_title, data2.categoryInfo.name)
                        .setVisible(R.id.v_hint, isChecked)
                        .setTextColor(R.id.tv_title, ContextCompat.getColor(frg.act, if (isChecked) R.color.color_d81b60 else R.color.color_333333))
                        .setOnClickListener(R.id.tv_title, {
                            frg.mPresenter.cookList(frg, data2.categoryInfo.ctgId, true)
                            frg.mPresenter.cid = data2.categoryInfo.ctgId
                            frg.mPresenter.position = helper.adapterPosition
                            notifyDataSetChanged()
                        })

            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    companion object {
        val TYPE_LEVEL_0 = 1
        val TYPE_LEVEL_1 = 2
    }

}