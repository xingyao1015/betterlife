package com.yc.betterlife.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.trello.rxlifecycle2.components.support.RxFragment

class MainVPAdapter constructor(fm:FragmentManager, var frgs:ArrayList<RxFragment>):FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return frgs[p0]
    }

    override fun getCount(): Int {
        return frgs.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


}