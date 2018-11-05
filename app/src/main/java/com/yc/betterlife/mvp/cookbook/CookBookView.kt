package com.yc.betterlife.mvp.cookbook

import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.entity.CookListListBean
import com.yc.betterlife.entity.CookTypesChild

interface CookBookView: BaseFrgView {
    fun showCookTypes(datas:List<CookTypesChild>)

    fun showCookList(datas:ArrayList<CookListListBean>)
}