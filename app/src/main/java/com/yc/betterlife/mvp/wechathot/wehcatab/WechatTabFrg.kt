package com.yc.betterlife.mvp.wechathot.wehcatab

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.Navigation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yc.betterlife.R
import com.yc.betterlife.adapter.WechatItemAdapter
import com.yc.betterlife.base.basefrg.BaseFrg
import com.yc.betterlife.entity.WechatItemResulListBean
import com.yc.betterlife.utils.Contract
import kotlinx.android.synthetic.main.frg_wechat_tab.*

class WechatTabFrg : BaseFrg<WechatHotTabView, WechatTabPresenter>(), WechatHotTabView, BaseQuickAdapter.OnItemClickListener {
    val mPresenter = WechatTabPresenter()
    var cid: Int = 0

    lateinit var mAdapter: WechatItemAdapter
    override val resId: Int = R.layout.frg_wechat_tab
    override fun initView() {
        this.swipe = my_swipe
        mAdapter = WechatItemAdapter(ArrayList())
        mAdapter.setEnableLoadMore(true)
        rv.layoutManager = LinearLayoutManager(act)
        rv.adapter = mAdapter
        cid = arguments!!.getInt(Contract.KEY_ID)
    }

    override fun initPresenter(): WechatTabPresenter {
        return mPresenter
    }

    override fun bindLisenter() {
        super.bindLisenter()
        mAdapter.setOnLoadMoreListener(this)
        mAdapter.setOnItemClickListener(this)
    }

    override fun initNetworkData(isRefresh: Boolean) {
        presenter.getData(this, cid, isRefresh)
    }

    override fun showData(data: ArrayList<WechatItemResulListBean>) {
        mAdapter.addData(data)
        mAdapter.loadMoreComplete()
    }

    override fun showNoMoreData() {
        mAdapter.loadMoreEnd()
    }

    override fun showLoadFailed() {
        mAdapter.loadMoreFail()
    }

    override fun clearDatas() {
        mAdapter.data.clear()
        mAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View, position: Int) {
        var data = adapter!!.data[position] as WechatItemResulListBean
        val bundle = Bundle()
        bundle.putString(Contract.KEY_URL, data.sourceUrl)
        Navigation.findNavController(view).navigate(R.id.action_main_frg_to_web_frg, bundle);
    }
}