package com.yc.betterlife.mvp.webact

import android.graphics.Bitmap
import android.view.View
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.yc.betterlife.R
import com.yc.betterlife.base.baseact.BaseAct
import com.yc.betterlife.utils.Contract
import kotlinx.android.synthetic.main.frg_base.*
import kotlinx.android.synthetic.main.act_web.*

class WebAct : BaseAct<WebFrgView, WebPresenter>(), WebFrgView {
    override val resId: Int = R.layout.act_web
    val mPresenter = WebPresenter()
    var url = ""
    override fun initView() {
        lv_title.visibility = View.VISIBLE
        url = intent!!.getStringExtra(Contract.KEY_URL)
        web.settings.javaScriptEnabled = true
        web.webViewClient = object : WebViewClient() {
            override fun onReceivedError(p0: WebView?, p1: WebResourceRequest?, p2: WebResourceError?) {
                showError(true)
            }

            override fun onReceivedSslError(p0: WebView?, p1: SslErrorHandler?, p2: SslError?) {
                p1?.proceed()
                showError(true)
            }

            override fun onReceivedError(p0: WebView?, p1: Int, p2: String?, p3: String?) {
                showError(true)
            }

            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                super.onPageStarted(p0, p1, p2)
                showLoading(true)
                web.visibility = View.INVISIBLE
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
                showLoading(false)
                showError(false)
                web.visibility = View.VISIBLE
            }
        }

        web.loadUrl(url)
    }

    override fun initPresenter(): WebPresenter {
        return mPresenter
    }

    override fun onBackPressed() {
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun back() {
        if (web.canGoBack()) {
            web.goBack()
        } else {
            super.back()
        }
    }

}