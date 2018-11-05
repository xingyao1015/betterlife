package com.yc.betterlife.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import butterknife.BuildConfig
import com.facebook.common.internal.Supplier
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.cache.MemoryCacheParams
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.mob.MobSDK
import com.tendcloud.tenddata.TCAgent
import com.yc.betterlife.di.retrofitManagerModule
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import java.util.concurrent.TimeUnit

class BetterApp : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(retrofitManagerModule)
    }

    companion object {
        lateinit var app: BetterApp
        private val MAX_MEM: Int = 30 * 1024 * 1024
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        var imag = Fresco.getImagePipeline()
        imag.clearMemoryCaches()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initFresco()
        MobSDK.init(this)
        TCAgent.LOG_ON=true;
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(this);
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(true);
    }

    private fun initFresco() {
        var httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        var cacheParams = MemoryCacheParams(
                MAX_MEM,
                Int.MAX_VALUE,
                MAX_MEM,
                Int.MAX_VALUE,
                Int.MAX_VALUE
        )

        var mSupplierMemoryCacheParams: Supplier<MemoryCacheParams> = Supplier {
            cacheParams
        }
        var config: ImagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(this, httpClient)
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)
                .build();
        Fresco.initialize(this, config)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}