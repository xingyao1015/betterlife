package com.yc.betterlife.mvp.cookbook.cookbookdetail

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.ThemedSpinnerAdapter
import com.yc.betterlife.R
import com.yc.betterlife.adapter.CookBookDetailAdapter
import com.yc.betterlife.base.baseact.BaseAct
import com.yc.betterlife.entity.CookBookDetailRecipeMethod
import com.yc.betterlife.entity.CookBookDetailResult
import com.yc.betterlife.utils.Contract
import kotlinx.android.synthetic.main.act_cookbook_detail.*

class CookBookDetailAct : BaseAct<CookBookDetailView, CookBookDetailPresenter>(), CookBookDetailView {

    override val resId: Int = R.layout.act_cookbook_detail

    val mPresenter = CookBookDetailPresenter()

    lateinit var id: String
    lateinit var mAdapter: CookBookDetailAdapter

    override fun initView() {
        id = intent.getStringExtra(Contract.KEY_ID)
        rv.layoutManager = LinearLayoutManager(this)
        rv.isNestedScrollingEnabled = false
        mAdapter = CookBookDetailAdapter(ArrayList())
        rv.adapter = mAdapter
    }

    override fun initNetworkData(isRefresh: Boolean) {
        mPresenter.cookBookDetail(this, id)
    }

    override fun initPresenter(): CookBookDetailPresenter {
        return mPresenter
    }

    override fun showContent(data: CookBookDetailResult) {
        tv_cook_title.text = data.name
        if (data.recipe.ingredients != null) {
            tv_ingredients.text = data.recipe.ingredients.replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
        }

        tv_type.text = data.ctgTitles
        if (data.recipe.sumary == null) {
            tv_sumary.text = "暂无简介"
        } else {
            tv_sumary.text = data.recipe.sumary
        }
        if (data.thumbnail != null) {
            iv.setImageURI(data.thumbnail)
        }
    }

    override fun showMethod(datas: ArrayList<CookBookDetailRecipeMethod>) {
        mAdapter.addData(datas)
    }
}