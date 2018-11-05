package com.yc.betterlife.mvp.cookbook.cookbookdetail

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yc.betterlife.base.baseact.BaseActPresenter
import com.yc.betterlife.base.baseact.BaseActView
import com.yc.betterlife.entity.CookBookDetail
import com.yc.betterlife.entity.CookBookDetailRecipeMethod
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CookBookDetailPresenter : BaseActPresenter<CookBookDetailView>() {
    lateinit var v: CookBookDetailView

    override fun attachView(v: BaseActView) {
        this.v = v as CookBookDetailView
    }

    fun cookBookDetail(frg: RxAppCompatActivity, id: String) {
        apiService.cookBookDetail(id)
                .compose(frg.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CookBookDetail> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: CookBookDetail) {
                        if (t.retCode == 200) {
                            v.showError(false)
                            v.showContent(t.result)
                            var method = t.result.recipe.method
                            method = method.replace("\\", "")
                            var gson = Gson()

                            var methods: ArrayList<CookBookDetailRecipeMethod> = gson.fromJson(method, object : TypeToken<ArrayList<CookBookDetailRecipeMethod>>() {}.type)
                            v.showMethod(methods)
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