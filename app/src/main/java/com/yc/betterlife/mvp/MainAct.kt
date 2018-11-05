package com.yc.betterlife.mvp

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yc.betterlife.R


class MainAct : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        val fragment = supportFragmentManager.findFragmentById(R.id.frgment)!!
        return NavHostFragment.findNavController(fragment).navigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}