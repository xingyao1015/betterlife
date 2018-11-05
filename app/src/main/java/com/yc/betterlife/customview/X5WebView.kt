package com.yc.betterlife.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm
import com.tencent.smtt.sdk.WebView

class X5WebView @SuppressLint("SetJavaScriptEnabled")
constructor(arg0: Context, arg1: AttributeSet) : WebView(arg0, arg1) {
    internal var title: TextView? = null

    init {
        initWebViewSettings()
        this.view.isClickable = true
    }

    private fun initWebViewSettings() {
        val webSetting = this.settings
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(true)
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true)
        // webSetting.setDatabaseEnabled(true);
        webSetting.domStorageEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE
        webSetting.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

}
