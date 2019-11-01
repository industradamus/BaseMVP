package com.wispcoolwisp.basemvp.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), MvpBaseView {

    override fun showLoading() {
        loadingView?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView?.visibility = View.GONE
    }
}