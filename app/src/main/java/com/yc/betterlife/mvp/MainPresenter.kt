package com.yc.betterlife.mvp

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter
import com.yc.betterlife.mvp.cookbook.CookBookFrg
import com.yc.betterlife.mvp.home.HomeFrg
import com.yc.betterlife.mvp.normalmanager.NormalManagerFrg
import com.yc.betterlife.mvp.wechathot.WechatHotFrg


class MainPresenter: BasePresenter<MainActView>()  {
    lateinit var v: MainActView

    override fun attachView(v: BaseFrgView) {
        this.v = v as MainActView;
    }

    fun initFrg(){
        var frgs = ArrayList<RxFragment>()

        frgs.add(HomeFrg())
        frgs.add(WechatHotFrg())
        frgs.add(CookBookFrg())
        frgs.add(NormalManagerFrg())
        v.showFrgs(frgs)
    }
}



