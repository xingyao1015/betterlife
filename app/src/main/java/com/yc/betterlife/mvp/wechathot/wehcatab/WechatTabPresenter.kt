package com.yc.betterlife.mvp.wechathot.wehcatab

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter
import com.yc.betterlife.entity.WechatItem
import com.yc.betterlife.utils.Contract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WechatTabPresenter : BasePresenter<WechatHotTabView>() {
    var v: WechatHotTabView? = null
    override fun attachView(v: BaseFrgView) {
        this.v = v as WechatHotTabView
    }

    fun getData(frg: RxFragment, cid: Int, isRefresh: Boolean) {
        if (isRefresh){
            PAGE_INDEX = 1
            v!!.startRefresh()
        }
        apiService.wechatSearch(cid, PAGE_INDEX, Contract.PER_PAGE)
                .compose(frg.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<WechatItem> {
                    override fun onComplete() {
                        v!!.stopRefresh()
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: WechatItem) {
                        if (t.retCode == 200) {
                            var data = t.result.list
                            if (data != null) {
                                if (isRefresh){
                                    v!!.clearDatas()
                                }
                                v!!.showData(data)
                            } else {
                                v!!.showNoMoreData()
                            }
                            PAGE_INDEX++
                        } else {
                            v!!.showLoadFailed()
                        }
                        v!!.stopRefresh()
                    }

                    override fun onError(e: Throwable) {
                        v!!.showLoadFailed()
                        v!!.stopRefresh()

                    }

                })
    }
}