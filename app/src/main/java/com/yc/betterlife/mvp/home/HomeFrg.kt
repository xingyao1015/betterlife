package com.yc.betterlife.mvp.home

import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.setPadding
import com.yc.betterlife.R
import com.yc.betterlife.base.basefrg.BaseFrg
import kotlinx.android.synthetic.main.frg_home.*

class HomeFrg : BaseFrg<HomeFrgView, HomeFrgPresenter>(), HomeFrgView {

    val mPresenter: HomeFrgPresenter = HomeFrgPresenter();

    override fun initNetworkData(isRefresh: Boolean) {

    }

    override val resId: Int = R.layout.frg_home

    override fun initView() {
        tv_content.setPadding(100)
    }


    override fun initPresenter(): HomeFrgPresenter {
        return mPresenter
    }


}