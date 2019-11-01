package com.wispcoolwisp.basemvp.base

import android.content.Context

abstract class BasePresenter<V : MvpBaseView> : MvpBasePresenter<V> {

    protected var mvpView: V? = null
        private set

    protected val context: Context
        get() {
            return if (mvpView is BaseFragment<*, *>) {
                (mvpView as BaseFragment<*, *>).requireContext()
            } else {
                mvpView as BaseActivity
            }
        }

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        mvpView = null
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }
}