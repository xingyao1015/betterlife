package com.yc.betterlife.mvp.wechathot

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.base.basefrg.BasePresenter
import com.yc.betterlife.entity.WechatTabs
import com.yc.betterlife.mvp.wechathot.wehcatab.WechatTabFrg
import com.yc.betterlife.utils.Contract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WechatHotPresenter : BasePresenter<WechatHotView>() {
    lateinit var v: WechatHotView
    override fun attachView(v: BaseFrgView) {
        this.v = v as WechatHotView
    }

    fun wechattabs(frg: RxFragment) {
        apiService.wechattabs()
                .compose(frg.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<WechatTabs> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: WechatTabs) {
                        if (t.retCode == 200) {
                            v.showError(false)
                            var results = t.result
                            if (results != null) {
                                var frgs = ArrayList<RxFragment>()
                                var titles = ArrayList<String>()
                                var frg: WechatTabFrg
                                var bundle: Bundle
                                for (res in results) {
                                    frg = WechatTabFrg()
                                    bundle = Bundle()
                                    bundle.putInt(Contract.KEY_ID, res.cid)
                                    frg.arguments = bundle
                                    frgs.add(frg)
                                    titles.add(res.name)
                                }
                                v.showFrgs(frgs, titles)
                            }
                        } else {
                            v.showError(true)
                        }
                    }

                    override fun onError(e: Throwable) {
                        v.showError(true)
                    }

                })
    }
}