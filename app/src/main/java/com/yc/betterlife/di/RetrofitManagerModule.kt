package com.yc.betterlife.di

import com.yc.betterlife.BuildConfig
import com.yc.betterlife.http.ApiService
import com.yc.betterlife.http.HttpConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val RETROFIT_MANAGER_MODULE_TAG = "retrofit_manager_module_tag"
const val RETROFIT_MANAGER_MODULE_INTERCEPTOR_TAG = "retrofit_manager_module_interceptor_tag"
const val RETROFIT_MANAGER_MODULE_INTERCEPTOR_LOG_TAG = "retrofit_manager_module_interceptor_log_tag"
const val CONNECT_TIME_OUT = 30L
val retrofitManagerModule = Kodein.Module(RETROFIT_MANAGER_MODULE_TAG) {

    bind<Interceptor>(RETROFIT_MANAGER_MODULE_INTERCEPTOR_TAG) with provider {
        object:Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var original = chain.request()
                var url = original.url().newBuilder()
                        .setEncodedQueryParameter("key",HttpConfig.APP_KEY_MOB).build()

                var request = original
                        .newBuilder()
                        .header("Autherization", "betterlife")
                        .header("App-name", "betterlife")
                        .header("App-Version", BuildConfig.VERSION_NAME)
                        .method(original.method(), original.body())
                        .url(url)
                        .build();
                return chain.proceed(request)
            }
        }
    }

    bind<HttpLoggingInterceptor>(RETROFIT_MANAGER_MODULE_INTERCEPTOR_LOG_TAG) with provider {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    bind<OkHttpClient.Builder>() with provider {
        OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
                .addInterceptor(instance(RETROFIT_MANAGER_MODULE_INTERCEPTOR_TAG))
                .addInterceptor(instance(RETROFIT_MANAGER_MODULE_INTERCEPTOR_LOG_TAG))
    }

    bind<OkHttpClient>() with provider{
        instance<OkHttpClient.Builder>().build()
    }

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(instance())
                .build()
    }


    bind<ApiService>() with provider { instance<Retrofit>().create(ApiService::class.java) }
}