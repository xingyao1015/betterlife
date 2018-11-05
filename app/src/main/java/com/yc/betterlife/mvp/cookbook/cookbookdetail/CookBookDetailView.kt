package com.yc.betterlife.mvp.cookbook.cookbookdetail

import com.yc.betterlife.base.baseact.BaseActView
import com.yc.betterlife.entity.CookBookDetail
import com.yc.betterlife.entity.CookBookDetailRecipeMethod
import com.yc.betterlife.entity.CookBookDetailResult

interface CookBookDetailView:BaseActView {

    fun showMethod(datas:ArrayList<CookBookDetailRecipeMethod>)

    fun showContent(data:CookBookDetailResult)
}