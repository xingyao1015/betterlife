package com.yc.betterlife.mvp.cookbook

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter
import com.yc.betterlife.entity.CookList
import com.yc.betterlife.entity.CookTypes
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CookBookPresenter : BasePresenter<CookBookView>() {
    lateinit var v: CookBookView
    var cid: String? = null
    var position = 1
    override fun attachView(v: BaseFrgView) {
        this.v = v as CookBookView
    }

    fun cookTypes(frg: RxFragment) {
        apiService.cookTypes()
                .compose(frg.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CookTypes> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: CookTypes) {
                        if (t.retCode == 200) {
                            var datas = t.result.childs;
                            if (datas != null) {
                                v.showError(false)
                                v.showCookTypes(datas)
                                cid = datas[0].childs?.get(0)!!.categoryInfo.ctgId
                                cookList(frg, cid!!, true)
                            } else {
                                v.showNoMoreData()
                            }
                        } else {
                            v.showLoadFailed()
                            v.showError(true)
                        }
                    }

                    override fun onError(e: Throwable) {
                        v.showLoadFailed()
                        v.showError(true)
                    }

                })
    }

    fun cookList(frg: RxFragment, cid: String?, isRefresh: Boolean) {
        if (cid == null) {
            return
        }
        if (isRefresh) {
            v.startRefresh()
            PAGE_INDEX = 1
        }
        apiService.cookListByCid(cid, PAGE_INDEX, 20)
                .compose(frg.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CookList> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: CookList) {
                        if (t.retCode == 200) {
                            var datas = t.result.list
                            if (datas != null) {
                                if (isRefresh) {
                                    v.clearDatas()
                                }
                                v.showCookList(datas)
                            }else{
                                v.showNoMoreData()
                            }
                            PAGE_INDEX++
                        }else{
                            v.showLoadFailed()
                        }
                        v.stopRefresh()
                    }

                    override fun onError(e: Throwable) {
                        v.showLoadFailed()
                        v.stopRefresh()
                    }

                })
    }

}