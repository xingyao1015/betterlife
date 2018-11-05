package com.yc.betterlife.mvp.wechathot

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.R
import com.yc.betterlife.adapter.CommenTabAdapter
import com.yc.betterlife.base.basefrg.BaseFrg
import kotlinx.android.synthetic.main.frg_wechathot.*

class WechatHotFrg : BaseFrg<WechatHotView, WechatHotPresenter>(), WechatHotView {
    val mPresenter = WechatHotPresenter()

    override val resId: Int = R.layout.frg_wechathot

    override fun initView() {

    }

    override fun initPresenter(): WechatHotPresenter {
        return mPresenter
    }

    override fun initNetworkData(isRefresh: Boolean) {
        presenter.wechattabs(this)
    }

    override fun showFrgs(frgs: ArrayList<RxFragment>, titles: ArrayList<String>) {
        var adatper = CommenTabAdapter(childFragmentManager, frgs, titles)
        vp.adapter = adatper
        tab.setupWithViewPager(vp)
    }


}