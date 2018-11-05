package com.yc.betterlife.base.baseact

import android.arch.lifecycle.LifecycleOwner
import com.yc.betterlife.base.BaseLifecycleObserver
import com.yc.betterlife.base.basefrg.BaseFrgView
import com.yc.betterlife.di.retrofitManagerModule
import com.yc.betterlife.http.ApiService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

abstract class BaseActPresenter<T:BaseActView>  : BaseLifecycleObserver, KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(retrofitManagerModule)
    }

    open val apiService: ApiService by instance()

    open var PAGE_INDEX = 1

    var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    abstract fun attachView(v: BaseActView)

    var lifecycleOwner: LifecycleOwner? = null


    override fun onCreate(owner: LifecycleOwner) {

    }

    override fun onStart(owner: LifecycleOwner) {

    }

    override fun onResume(owner: LifecycleOwner) {

    }

    override fun onPause(owner: LifecycleOwner) {

    }

    override fun onStop(owner: LifecycleOwner) {

    }

    override fun onDestroy(owner: LifecycleOwner) {

    }
}