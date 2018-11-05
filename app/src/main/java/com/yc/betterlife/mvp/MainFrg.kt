package com.yc.betterlife.mvp

import android.view.View
import android.widget.RadioGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.R
import com.yc.betterlife.adapter.MainVPAdapter
import com.yc.betterlife.base.basefrg.BaseFrg
import com.yc.betterlife.customview.BottomBar
import com.yc.betterlife.customview.BottomBarTab
import kotlinx.android.synthetic.main.frg_base.*
import kotlinx.android.synthetic.main.frg_main.*


class MainFrg : BaseFrg<MainActView, MainPresenter>(), MainActView, RadioGroup.OnCheckedChangeListener {
    override val resId: Int = R.layout.frg_main
    val mPresenter = MainPresenter()

    override fun initView() {
        lv_title.visibility =View.GONE
        mPresenter.initFrg()
        bottomBar.addItem(BottomBarTab(act, R.drawable.ic_home_white_24dp))
                .addItem(BottomBarTab(act, R.drawable.ic_discover_white_24dp))
                .addItem(BottomBarTab(act, R.drawable.ic_message_white_24dp))
                .addItem(BottomBarTab(act, R.drawable.ic_account_circle_white_24dp))

        bottomBar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int, prePosition: Int) {
                vp.setCurrentItem(position)
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabReselected(position: Int) {

            }

        })
    }

    override fun initPresenter(): MainPresenter {
        return mPresenter
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

    }

    override fun showFrgs(frgs: ArrayList<RxFragment>) {
        vp.adapter = MainVPAdapter(childFragmentManager, frgs)
        vp.offscreenPageLimit = 4
        bottomBar.setCurrentItem(0)
    }
}