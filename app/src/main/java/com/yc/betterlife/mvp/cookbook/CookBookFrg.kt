package com.yc.betterlife.mvp.cookbook

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import com.yc.betterlife.R
import com.yc.betterlife.adapter.CookListAdapter
import com.yc.betterlife.adapter.CookTypeAdapter
import com.yc.betterlife.base.basefrg.BaseFrg
import com.yc.betterlife.entity.CookBookDetail
import com.yc.betterlife.entity.CookListListBean
import com.yc.betterlife.entity.CookTypesChild
import com.yc.betterlife.mvp.cookbook.cookbookdetail.CookBookDetailAct
import com.yc.betterlife.utils.Contract
import kotlinx.android.synthetic.main.frg_cookbook.*

class CookBookFrg : BaseFrg<CookBookView, CookBookPresenter>(), CookBookView,
        BaseQuickAdapter.OnItemClickListener {

    val mPresenter = CookBookPresenter()
    lateinit var typeAdapter: CookTypeAdapter
    lateinit var listAdapter: CookListAdapter
    override val resId: Int = R.layout.frg_cookbook

    override fun initView() {
        this.swipe = my_swipe
        rv_type.layoutManager = LinearLayoutManager(act)
        rv_type.addItemDecoration(PinnedHeaderItemDecoration
                .Builder(CookTypeAdapter.TYPE_LEVEL_0)
                .enableDivider(false)
                .disableHeaderClick(true)
                .create())
        rv_list.layoutManager = LinearLayoutManager(act)
        listAdapter = CookListAdapter(ArrayList())
        rv_list.adapter = listAdapter
    }

    override fun initPresenter(): CookBookPresenter {
        return mPresenter
    }

    override fun initNetworkData(isRefresh: Boolean) {
        mPresenter.cookList(this, mPresenter.cid, isRefresh)
    }

    override fun bindLisenter() {
        super.bindLisenter()
        listAdapter.setOnLoadMoreListener(this)
        listAdapter.setOnItemClickListener(this)
    }

    override fun getNetDataOnce() {
        mPresenter.cookTypes(this)
    }

    override fun showCookTypes(datas: List<CookTypesChild>) {
        var entitys: ArrayList<MultiItemEntity> = ArrayList()
        for (data in datas) {
            var childs = data.childs
            for (child in childs) {
                data.addSubItem(child)
            }
            entitys.add(data)
        }
        typeAdapter = CookTypeAdapter(this, entitys)
        typeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
        typeAdapter.expandAll();
        rv_type.adapter = typeAdapter
        typeAdapter.loadMoreComplete()
    }

    override fun showCookList(datas: ArrayList<CookListListBean>) {
        listAdapter.addData(datas)
        listAdapter.loadMoreComplete()
    }

    override fun showLoadFailed() {
        listAdapter.loadMoreFail()
    }

    override fun showNoMoreData() {
        listAdapter.loadMoreEnd()
    }

    override fun clearDatas() {
        listAdapter.data.clear()
        listAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        var intent = Intent(act, CookBookDetailAct::class.java)

        var data = adapter.data[position] as CookListListBean
        intent.putExtra(Contract.KEY_ID, data.menuId)
        startActivity(intent)
    }
}