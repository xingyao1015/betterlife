package com.yc.betterlife

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yc.betterlife.mvp.MainAct
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Observable.timer(1000,TimeUnit.MILLISECONDS)
                .subscribe { along ->
                    startActivity(Intent(this,MainAct::class.java))
                    finish()
                }
    }
}